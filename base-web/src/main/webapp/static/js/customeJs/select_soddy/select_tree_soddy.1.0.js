/**
 * Created by soddy on 2016/11/27.
 */
if (typeof module !== "undefined" && typeof exports !== "undefined" && module.exports === exports) {
    module.exports = 'selectTree';
}
(function (window, document, $, angular) {
    'use strict';

    /**
     * 多选slect框
     */
    angular.module('selectTree.mutil',["selectTree.defaultParam"]).directive("mutilTreeSelect", mutilTreeSelect);

    function mutilTreeSelect($q, $http, $document, $scope) {
        compileDirective.$inject = ['tElm'];
        ControllerDirective.$inject = ['$scope'];
        return {
            restrict: 'A',
            templateUrl: 'src/select_tree_view.tpl.html',
            scope: {
                dtUrl: '=',
                dtData: '=?',
                dtParams: '='
            },
            compile: compileDirective,
            controller: ControllerDirective
        };
        function compileDirective(tElm) {
            var _staticHTML = tElm[0].innerHTML;
            return {
                pre: function preLink($scope, $elem, iAttrs, ctrl) {
                    // 前置处理
                },
                post: function postLink($scope, $elem, iAttrs, ctrl) {
                    var defer = $q.defer();
                    var promise = defer.promise;
                    if (typeof $scope.dtUrl != "undefined") {
                        promise = $http({
                            url: $scope.dtUrl,
                            method: 'POST',
                            data: {}
                        })
                    } else {
                        defer.resolve();
                    }
                    promise.then(function (resp) {
                        if (typeof resp != "undefined") {
                            var result = resp.data;//返回对象的data属性
                            $scope.dtData = result;
                        }
                    }).catch(function (e) {
                        console.log(e);
                    }).finally(function () {
                    })
                }
            }
        }

        function ControllerDirective($scope) {
            // $scope.dtData ;//初始化的参数

        }

    }


    //递归的子树
    angular.module('selectTree.child',["selectTree.defaultParam"]).directive("itemChildTree",itemChildTree);
    function itemChildTree($q, $http, $document, $scope) {
        return {
            restrict: 'A',
            templateUrl: 'src/select_tree_child.tpl.html',
            scope: {
                dtUrl: '=',
                dtData: '=?',
                dtParams: '='
            },
            compile: compileDirective,
            controller: ControllerDirective
        };

    }

    /**
     * 默认参数设定
     */
    angular.module("selectTree.defaultParam").controller("selectTree.defaultParamCtrl",defaultParamCtrl);
    function defaultParamCtrl($scope,$http,$q) {

        /**
         * tree Node define
         * @type {{id: string, pid: string, code: string, title: string, content: string, hasNext: boolean, hasPrev: boolean, isExtend: boolean, ifLastNode: boolean, index: number}}
         */
        var treeData = {
            id:"",
            pid:"",
            code:"",
            title:"",
            content:"",
            hasNext:false,
            hasPrev:false,
            isExtend:false,
            ifLastNode:false,
            index:0
        };
        var defaultParam = {
            treeType:"radio",//radio or checkbox
            process:"server",//client or server
            cacheLevel:"2",//cacheLevel 点击的子节点，自动缓存下面2层的数据
        };



        $scope._defaultTreeNode = treeData;
        $scope._defaultParam = defaultParam;

    }

})(window, document, jQuery, angular);