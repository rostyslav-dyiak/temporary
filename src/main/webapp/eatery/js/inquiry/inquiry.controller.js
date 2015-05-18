(function () {
    'use strict';

    app.controller('InquiryController', InquiryController);

    InquiryController
        .$inject = [
        '$scope',
        'toaster',
        'InquiryFactory'
    ];

    function InquiryController($scope, toaster, InquiryFactory) {
        $scope.inquiries = [];
        $scope.searchQuery = '';
        $scope.selectedInquiry = {};

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
