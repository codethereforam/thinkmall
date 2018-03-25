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

    var updateFormFieldset = $('#updateForm').find('fieldset');

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
            $('input[name="deleted"]')[1].checked = true;
        } else {
            $('input[name="deleted"]')[0].checked = true;
        }
        inputCreateTime.val(treeNode.createTime);
    }

    // 类别选择树节点被点击相应的事件
    function onCategorySelectTreeNodeClick(event, treeId, treeNode) {
        if(treeNode.level >= originalLevel) {
            $('#errorHintContent').text('不能降低原级别，请选择比原级别小的父类别');
            $('#errorHintModal').modal();
            return;
        }
        inputParentName.val(treeNode.name);
        inputParentId.val(treeNode.categoryId);
        inputLevel.val(treeNode.level + 1);
        parentSelectModal.modal('hide');
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
                for (var i in allCategories) {
                    var category = allCategories[i];
                    if (!category.deleted) {
                        enabledCategories.push(category);
                    }
                }
                console.log(enabledCategories);
                // 初始化树为启用类别
                $.fn.zTree.init(categoryTreeBlock, categoryTreeSetting, enabledCategories);
            } else {
                //TODO: redirect to error page
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
            $.fn.zTree.init(categoryTreeBlock, categoryTreeSetting, categories);
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
                deleted: $('input[name="deleted"]:checked').val(),
                createTime: inputCreateTime.val()
            };
            console.log(category);
            $.post(BASE_PATH + '/manage/category/update', category, function (data) {
                console.log(data);
            });
        });
    }

    function init() {
        // disable form by default
        updateFormFieldset.attr("disabled", "disabled");

        getCategoriesAndInitTree();
        initFilterByDeleted();
        initBindSelectParentEvent();
        initUpdateListener();
    }

    $(document).ready(function () {
        init();
    });
});