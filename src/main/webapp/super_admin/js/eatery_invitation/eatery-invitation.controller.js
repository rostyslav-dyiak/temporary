(function () {
    'use strict';

    app.controller('EateryInvitationController', EateryInvitationController);

    EateryInvitationController
        .$inject = [
        '$scope',
        'toaster',
        'EateryInvitationFactory'
    ];

    function EateryInvitationController($scope, toaster, EateryInvitationFactory) {
        $scope.eateryInvitation = {};

        $scope.saveType = saveType;
        $scope.editType = editType;
        $scope.removeType = removeType;
        $scope.newBusinessType = newBusinessType;
        $scope.cancel = cancel;

        activate();

        function activate() {
            EateryInvitationFactory.query({},
                function (data) {
                    $scope.types = angular.copy(data);
                    //Delete this
                    for (var i = 0; i < $scope.types.length; i++) {
                        $scope.types[i].order = i;
                    }
                    // End of delete
                    $scope.types.sort(function (a, b) {
                        return a.order > b.order;
                    });
                }, function (e) {
                    console.error(e);
                });
        }

        $scope.sortableOptions = {
            stop: function () {
                for (var index in $scope.types) {
                    $scope.types[index].order = index;
                }
            }
        };

        function saveType() {
            BusinessTypeFactory.update({
                id: $scope.type.id,
                name: $scope.type.name,
                description: $scope.type.description
            }, function (data) {
                toaster.pop('success', 'Success', 'Business type saved');
                activate();
            }, function (e) {
                console.error(e);
                toaster.pop('error', 'Error', 'Please try again');
            });

        }

        function newBusinessType() {
            $scope.type = {};
            $scope.newType = true;
        }

        function removeType(id) {
            BusinessTypeFactory.delete({
                    id: id
                },
                function () {
                    toaster.pop('success', 'Success', 'Business type removed');
                    activate();
                }, function (e) {
                    console.error(e);
                    toaster.pop('error', 'Error', 'Please try again');
                });
        }

        function editType(type) {
            $scope.newType = false;
            $scope.selectedType = type;
            $scope.type.id = type.id;
            $scope.type.name = type.name;
            $scope.type.description = type.description;
        }

        function cancel() {
            $scope.type = {};
        }

    }
})();
