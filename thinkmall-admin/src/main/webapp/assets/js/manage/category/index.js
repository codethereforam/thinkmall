jQuery(function ($) {
    var categoryTreeBlock = $("#categoryTreeBlock");
    var treeSetting = {
        data: {
            key: {
                title: "description"
            },
            simpleData: {
                enable: true,
                idKey: "categoryId",
                pIdKey: "parentId"
            }
        }
    };
    // 所有类别
    var allCategories = [];
    //启用类别
    var enabledCategories = [];

    // 获取所有类别并初始化类别树
    function getCategoriesAndInitTree() {
        $.get(BASE_PATH + "/manage/category/list", function (data) {
            if (data.success) {
                allCategories = data.data;
                console.log(allCategories);
                for (var i in allCategories) {
                    var category = allCategories[i];
                    if (!category.deleted) {
                        enabledCategories.push(category);
                    }
                }
                console.log(enabledCategories);
                // 初始化树为启用类别
                $.fn.zTree.init(categoryTreeBlock, treeSetting, enabledCategories);
            } else {
                //TODO: redirect to error page
            }
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
            $.fn.zTree.init(categoryTreeBlock, treeSetting, categories);
        });
    }

    function init() {
        getCategoriesAndInitTree();
        initFilterByDeleted();
    }

    $(document).ready(function () {
        init();
    });
});