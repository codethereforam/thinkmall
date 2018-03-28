jQuery(function ($) {
    var categoryTreeBlock = $("#categoryTreeBlock");
    var treeSettingData = {
        key: {
            title: "description"
        },
        simpleData: {
            enable: true,
            idKey: "categoryId",
            pIdKey: "parentId"
        }
    };
    var categoryTreeSetting = {
        data: treeSettingData,
        callback: {
            onClick: onCategoryTreeNodeClick
        }
    };
    var categorySelectTreeSetting = {
        data: treeSettingData,
        callback: {
            onClick: onCategorySelectTreeNodeClick
        }
    };
    var categoryAddSelectTreeSetting = {
        data: treeSettingData,
        callback: {
            onClick: onCategoryAddSelectTreeNodeClick
        }
    };
    // 所有类别
    var allCategories = [];
    //启用类别
    var enabledCategories = [];

    var inputCategoryId = $('#inputCategoryId');
    var inputName = $('#inputName');
    var inputDescription = $('#inputDescription');
    var inputParentId = $('#inputParentId');
    var inputLevel = $('#inputLevel');
    var inputCreateTime = $('#inputCreateTime');
    var inputParentName = $('#inputParentName');
    var originalLevel;

    var updateForm = $('#updateForm');
    var updateFormFieldset = updateForm.find('fieldset');

    var categoriesListTree;

    // 类别树节点被点击相应的事件
    function onCategoryTreeNodeClick(event, treeId, treeNode) {
        var parentNode = treeNode.getParentNode();
        if (parentNode === null) {
            updateFormFieldset.attr("disabled", "disabled");
        } else {
            updateFormFieldset.removeAttr("disabled");
        }
        inputCategoryId.val(treeNode.categoryId);
        inputName.val(treeNode.name);
        inputDescription.val(treeNode.description);
        inputParentName.val(parentNode === null ? '无' : parentNode.name);
        inputParentId.val(parentNode === null ? -1 : parentNode.categoryId);
        inputLevel.val(treeNode.level);
        originalLevel = treeNode.level;
        if (treeNode.deleted) {
            updateForm.find('input[name="deleted"]')[1].checked = true;
        } else {
            updateForm.find('input[name="deleted"]')[0].checked = true;
        }
        inputCreateTime.val(treeNode.createTime);
    }

    var hintModal = $('#hintModal');
    var hintContent = $('#hintContent');
    var hintModalHeader = hintModal.find('.modal-header');
    var hintModalBody = hintModal.find('.modal-body');

    // 显示提示框
    function showHintModal(message, success) {
        if (success) {
            hintModalHeader.attr('class', 'modal-header bg-success');
            hintModalBody.attr('class', 'modal-body text-success');
        } else {
            hintModalHeader.attr('class', 'modal-header bg-warning');
            hintModalBody.attr('class', 'modal-body text-warning');
        }
        hintContent.text(message);
        hintModal.modal();
    }

    // 类别选择树节点被点击相应的事件
    function onCategorySelectTreeNodeClick(event, treeId, treeNode) {
        if (treeNode.level >= originalLevel) {
            showHintModal('不能降低原级别，请选择比原级别小的父类别', false);
            return;
        }
        inputParentName.val(treeNode.name);
        inputParentId.val(treeNode.categoryId);
        inputLevel.val(treeNode.level + 1);
        parentSelectModal.modal('hide');
    }

    function onCategoryAddSelectTreeNodeClick(event, treeId, treeNode) {
        if (treeNode.level === 3) {
            showHintModal('不能选择三级类别，请重新选择', false);
            return;
        }
        inputAddParentName.val(treeNode.name);
        inputAddParentId.val(treeNode.categoryId);
        inputAddLevel.val(treeNode.level + 1);
        parentSelectAddModal.modal('hide');
    }

    // 获取所有类别并初始化类别树
    function getCategoriesAndInitTree() {
        $.get(BASE_PATH + "/manage/category/list", function (data) {
            if (data.success) {
                allCategories = data.data;
                var rootCategory = {
                    categoryId: 0,
                    name: "根类别",
                    description: "根类别",
                    level: 0,
                    deleted: false,
                    open: true
                };
                allCategories.push(rootCategory);
                console.log(allCategories);
                enabledCategories = [];
                for (var i in allCategories) {
                    var category = allCategories[i];
                    if (!category.deleted) {
                        enabledCategories.push(category);
                    }
                }
                console.log(enabledCategories);
                // 初始化树为启用类别
                categoriesListTree = $.fn.zTree.init(categoryTreeBlock, categoryTreeSetting, enabledCategories);
            } else {
                window.location.href = BASE_PATH + "/manage/errorPage";
            }
        });
    }

    var parentSelectModal = $('#parentSelectModal');
    var categorySelectTreeBlock = $('#categorySelectTreeBlock');

    function initBindSelectParentEvent() {
        $('#parentSelectBtn, #inputParentName').click(function () {
            // set parentSelectModal content
            $.fn.zTree.init(categorySelectTreeBlock, categorySelectTreeSetting, enabledCategories);
            // open modal
            parentSelectModal.modal();
        });
    }

    var parentSelectAddModal = $('#parentSelectAddModal');
    var categorySelectTreeAddBlock = $('#categorySelectTreeAddBlock');

    function initBindAddSelectParentEvent() {
        $('#parentSelectAddBtn, #inputAddParentName').click(function () {
            // set parentSelectModal content
            $.fn.zTree.init(categorySelectTreeAddBlock, categoryAddSelectTreeSetting, enabledCategories);
            // open modal
            parentSelectAddModal.modal();
        });
    }

    // 根据类别启用状态过滤
    function initFilterByDeleted() {
        $("#filterByDeletedMenu").find("li a").click(function () {
            var text = $(this).text();
            $("#filterByDeletedBtn").find(".text").text(text);
            var option = $(this).attr("class").trim();
            var categories;
            switch (option) {
                case "all-category":
                    categories = allCategories;
                    break;
                case "enabled-category":
                    categories = enabledCategories;
                    break;
                default:
            }
            categoriesListTree = $.fn.zTree.init(categoryTreeBlock, categoryTreeSetting, categories);
        });
    }

    function initUpdateListener() {
        $('#updateBtn').click(function () {
            // report validity
            var valid = document.querySelector("#updateForm").reportValidity();
            if (!valid) {
                return;
            }
            // send post request
            var category = {
                categoryId: inputCategoryId.val(),
                name: inputName.val(),
                description: inputDescription.val(),
                parentId: inputParentId.val(),
                level: inputLevel.val(),
                deleted: updateForm.find('input[name="deleted"]:checked').val(),
                createTime: inputCreateTime.val()
            };
            console.log(category);
            $.post(BASE_PATH + '/manage/category/update', category, function (data) {
                if (data.success) {
                    reset();
                    showHintModal('修改成功', true);
                } else {
                    showHintModal(data.data + ', 更新失败', false);
                }
            });
        });
    }

    var inputAddName = $('#inputAddName');
    var inputAddDescription = $('#inputAddDescription');
    var inputAddParentId = $('#inputAddParentId');
    var inputAddLevel = $('#inputAddLevel');
    var inputAddParentName = $('#inputAddParentName');
    var addForm = $('#addForm');
    var addModal = $('#addModal');

    function initAddListener() {
        $('#addBtn').click(function () {
            // report validity
            var valid = document.querySelector("#addForm").reportValidity();
            if (!valid) {
                return;
            }
            // send post request
            var category = {
                name: inputAddName.val(),
                description: inputAddDescription.val(),
                parentId: inputAddParentId.val(),
                level: inputAddLevel.val(),
                deleted: addForm.find('input[name="deleted"]:checked').val()
            };
            console.log(category);
            $.post(BASE_PATH + '/manage/category/add', category, function (data) {
                // if data is the error page
                if (typeof data !== 'object') {
                    $('html').html(data);
                    return;
                }
                if (data.success) {
                    reset();
                    showHintModal('添加成功', true);
                    // hide add modal
                    addModal.modal('hide');
                } else {
                    showHintModal(data.data[0].errorMsg + ', 添加失败', false);
                }
            });
        });
    }

    function disableUpdateForm() {
        updateFormFieldset.attr("disabled", "disabled");
    }

    // 重置页面
    function reset() {
        getCategoriesAndInitTree();
        // reset update form
        document.querySelector('#updateForm').reset();
        // reset add form
        document.querySelector('#addForm').reset();
        disableUpdateForm();
        $("#filterByDeletedBtn").find(".text").text('启用类别');
    }

    function initRefreshListener() {
        $("#refreshBtn").click(function () {
            reset();
        });
    }

    function initAddCategoryListener() {
        $("#addCategoryBtn").click(function () {
            var selectedCategories = categoriesListTree.getSelectedNodes();
            if(selectedCategories.length === 0) {
                return;
            }
            var parent = selectedCategories[0];
            if(parent.level === 3) {
               return;
            }
            inputAddParentId.val(parent.categoryId);
            inputAddParentName.val(parent.name);
            inputAddLevel.val(parent.level + 1);
        });
    }

    function init() {
        // disable form by default
        disableUpdateForm();
        getCategoriesAndInitTree();
        initFilterByDeleted();
        initBindSelectParentEvent();
        initUpdateListener();
        initAddListener();
        initRefreshListener();
        initBindAddSelectParentEvent();
        initAddCategoryListener();
    }

    $(document).ready(function () {
        init();
    });
});