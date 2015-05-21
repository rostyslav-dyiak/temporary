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
        $scope.selectedInquiryHistory = [];

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
                if(inquiry.parent){
                    if(inquiry.seenDate){
                        inquiry.reply = {status: 'Viewed',date: inquiry.createdDate};
                    }else{
                        inquiry.reply = {status: 'New Reply',date: inquiry.createdDate};
                    }
                }else{
                    inquiry.reply = {status: 'No Reply',date: inquiry.createdDate};
                }
                for(var j = 0; j < inquiry.inquiryOutlets.length; j++){
                    if(inquiry.inquiryOutlets[j].schedule != null){
                        inquiry.inquiryOutlets[j].schedule = getBooleanArray(inquiry.inquiryOutlets[j].schedule);
                    }
                }
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

        function getBooleanArray(daysText) {
            return daysText.split('_').map(function (x) {
                return parseInt(x) != 0
            });
        }

        function search() {
            // $scope.searchQuery
            console.log("Sending request... " + $scope.searchQuery);
        }

        function selectInquiry(inquiry) {
            $scope.selectedInquiry = inquiry;
            var parent = inquiry;
            do{
                $scope.selectedInquiryHistory.unshift(parent);
                parent = parent.parent;
            } while(parent);
            if(inquiry.parent && !inquiry.seenDate){
                InquiryFactory.updateLast({id: inquiry.id},
                    function (data) {
                        inquiry.seenDate = data.seenDate;
                        inquiry.reply = {status: 'Viewed',date: inquiry.createdDate};
                    }, function (e) {
                    });
            }
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
                function () {
                    toaster.pop('success', 'Success', 'Inquiry has been added to a supplier');
                }, function (e) {
                    console.error(e);
                    toaster.pop('error', 'Error', 'Please try again');
                });
        }
    }
})();
