(function () {
    'use strict';

    app.controller('InquiryController', InquiryController);

    InquiryController
        .$inject = [
        '$scope',
        'toaster',
        'InquiryFactory',
        'InquiryReplyFactory'
    ];

    function InquiryController($scope, toaster, InquiryFactory, InquiryReplyFactory) {
        $scope.inquiries = [];
        $scope.searchQuery = '';
        $scope.selectedInquiry = {};
        $scope.selectedInquiryResponses = [];
        $scope.latestInquiryRespond = [];

        $scope.search = search;
        $scope.selectInquiry = selectInquiry;
        $scope.deleteInquiry = deleteInquiry;
        $scope.addToSupplier = addToSupplier;

        activate();

        function activate() {
            InquiryFactory.query({},
                function (data) {
                    $scope.inquiries = data;
                }, function (e) {
                    console.error(e);
                });
        }

        function search() {
            // $scope.searchQuery
            console.log("Sending request... " + $scope.searchQuery);
        }

        function selectInquiry(inquiry) {
            $scope.selectedInquiry = inquiry;
            InquiryReplyFactory.query({
                id: inquiry.id
            }, function (data) {
                $scope.selectedInquiryResponses = data;
                $scope.latestInquiryRespond = data[data.length - 1];
            },  function (e) {
                console.error(e);
            });
        }

        function deleteInquiry() {
            InquiryFactory.delete({
                    id: $scope.selectedInquiry.id
                },
                function () {
                    toaster.pop('success', 'Success', 'Inquiry has been deleted');
                }, function (e) {
                    console.error(e);
                    toaster.pop('error', 'Error', 'Please try again');
                });
        }

        function addToSupplier() {
            InquiryFactory.update($scope.selectedInquiry,
                function (data) {
                    toaster.pop('success', 'Success', 'Inquiry has been added to a supplier');
                }, function (e) {
                    console.error(e);
                    toaster.pop('error', 'Error', 'Please try again');
                });
        }
    }
})();
