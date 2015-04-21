(function() {
    'use strict';

    angular.module('app')
        .directive('backButton', backButton);

    function backButton() {
        return {
            restrict: 'A',

            link: function(scope, element, attrs) {
                element.bind('click', goBack);

                function goBack() {
                    history.back();
                    scope.$apply();
                }
            }
        }
    }

})();
