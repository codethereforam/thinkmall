jQuery(function ($) {
    function initTree() {
        $.get(BASE_PATH + "/manage/category/list", function (data) {
            if (data.success) {
                var zNodes = data.data;
                console.log(zNodes);
                var setting = {
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
                $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            } else {
                //TODO: redirect to error page
            }
        });
    }


    function init() {
        initTree();
    }

    $(document).ready(function () {
        init();
    });
});