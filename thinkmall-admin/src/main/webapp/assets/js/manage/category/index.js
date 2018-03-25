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

    var inputName = $('#inputName');
    var inputParentName = $('#inputParentName');
    var inputParentId = $('#inputParentId');
    var inputDescription = $('#inputDescription');

    var updateFormFieldset = $('#updateForm').find('fieldset');

    // 类别树节点被点击相应的事件
    function onCategoryTreeNodeClick(event, treeId, treeNode) {
        var parentNode = treeNode.getParentNode();
        if (parentNode === null) {
            updateFormFieldset.attr("disabled", "disabled");
        } else {
            updateFormFieldset.removeAttr("disabled");
        }
        inputName.val(treeNode.name);
        inputParentName.val(parentNode === null ? '无' : parentNode.name);
        inputParentId.val(parentNode === null ? -1 : parentNode.categoryId);
        console.log(inputParentId.val());
        // console.log($('input[name="deleted"]:checked').val());
        if (treeNode.deleted) {
            $('input[name="deleted"]')[1].checked = true;
        } else {
            $('input[name="deleted"]')[0].checked = true;
        }
        inputDescription.val(treeNode.description);
    }

    // 类别选择树节点被点击相应的事件
    function onCategorySelectTreeNodeClick(event, treeId, treeNode) {
        if (treeNode.level === 3) {
            $('#parentCannotSelectModal').modal();
        } else {
            inputParentName.val(treeNode.name);
            inputParentId.val(treeNode.categoryId);
            parentSelectModal.modal('hide');
        }
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
                    leaf: false,
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

    function init() {
        // disable form by default
        updateFormFieldset.attr("disabled", "disabled");

        getCategoriesAndInitTree();
        initFilterByDeleted();
        initBindSelectParentEvent();
    }

    $(document).ready(function () {
        init();
    });
});