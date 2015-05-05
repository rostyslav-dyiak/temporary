(function() {
    'use strict';

    angular.module('app')
        .directive('preventDefault', preventDefault);

    function preventDefault() {
        return {
            restrict: 'A',

            link: function(scope, element, attrs) {
                element.bind('click', prevent);

                function prevent() {
                    event.preventDefault();
                    event.stopPropagation();
                }
            }
        }
    }

})();
