(function () {
    'use strict';

    app.controller('InquiryController', InquiryController);

    InquiryController
        .$inject = [
        '$scope',
        'toaster',
        'InquiryFactory',
        'InquiryReplyFactory',
        'CompanyFactory'
    ];

    function InquiryController($scope, toaster, InquiryFactory, InquiryReplyFactory, CompanyFactory) {
        $scope.inquiries = [];
        $scope.rowCollection = [];
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
                    $scope.rowCollection = data;
                    initializeInquiries();
                }, function (e) {
                });
        }

        function initializeInquiries(){
            for(var i = 0; i < $scope.rowCollection.length; i++){
                var inquiry = $scope.rowCollection[i];
                initializeSupplier(inquiry);
                initializeEnquiry(inquiry);
            }
        }

        function initializeSupplier(inquiry){
            CompanyFactory.get({
                    type: 'supplier',
                    id: inquiry.supplierDetails.id
                },
                function (data) {
                    inquiry.supplierDetails.supplier = data;
                }, function (e) {
                    console.error(e);
                });
        }

        function initializeEnquiry(inquiry){
            CompanyFactory.get({
                    type: 'eatery',
                    id: inquiry.eateryDetails.id
                },
                function (data) {
                    inquiry.eateryDetails.eatery = data;
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
            //$scope.latestInquiryRespond = $scope.selectedInquiry;

            //InquiryReplyFactory.query({
            //    id: inquiry.id
            //}, function (data) {
            //    $scope.selectedInquiryResponses = data;
            //    $scope.latestInquiryRespond = data[data.length - 1];
            //},  function (e) {
            //    console.error(e);
            //});
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
