(function () {
    'use strict';

    app.controller('InquiryController', InquiryController);

    InquiryController
        .$inject = [
        '$scope',
        '$modal',
        'toaster',
        'InquiryFactory',
        'InquiryReplyFactory',
        'PricingGroupFactory',
        'PaymentFactory',
        'TeamFactory'
    ];

    function InquiryController($scope, $modal, toaster, InquiryFactory, InquiryReplyFactory, PricingGroupFactory, PaymentFactory, TeamFactory) {
        $scope.inquiries = [];
        $scope.rowCollection = [];
        $scope.searchQuery = '';
        $scope.selectedInquiry = {};
        $scope.selectedInquiryResponses = [];
        $scope.latestInquiryRespond = [];
        $scope.teamMembers = [];
        $scope.priceGroups = [];
        $scope.paymentTerms = [];
        $scope.reply = {};
        $scope.showReplyPanel = false;

        $scope.search = search;
        $scope.selectInquiry = selectInquiry;
        $scope.openReply = openReply;
        $scope.useDefaultDays = useDefaultDays;
        $scope.validateDay = validateDay;
        $scope.discard = discard;
        $scope.send = send;
        $scope.openEateryProfileModal = openEateryProfileModal;

        activate();

        function activate() {
            InquiryFactory.query({},
                function (data) {
                    $scope.inquiries = data;
                    $scope.rowCollection = data;
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
                for (var i = 0; i < $scope.selectedInquiryResponses.length; i++) {
                    for (var j = 0; j < $scope.selectedInquiryResponses[i].inquiryOutlets.length; j++) {
                        $scope.selectedInquiryResponses[i].inquiryOutlets[j].schedule = getBooleanArray($scope.selectedInquiryResponses[i].inquiryOutlets[j].schedule);
                    }
                }
                $scope.latestInquiryRespond = data[data.length - 1];
            }, function (e) {
                console.error(e);
            });
        }

        function openReply() {
            $scope.showReplyPanel = true;
            PricingGroupFactory.query({},
                function (data) {
                    $scope.priceGroups = data;
                }, function (e) {
                    console.error(e);
                });
            PaymentFactory.query({},
                function (data) {
                    $scope.paymentTerms = data;
                }, function (e) {
                    console.error(e);
                });
            TeamFactory.query({},
                function (data) {
                    $scope.teamMembers = data;
                }, function (e) {
                    console.error(e);
                });
            $scope.reply.inquiryProducts = $scope.selectedInquiry.inquiryProducts;
            if($scope.latestInquiryRespond) {
                $scope.reply.inquiryOutlets = $scope.latestInquiryRespond.inquiryOutlets;
            } else {
                $scope.reply.inquiryOutlets = $scope.selectedInquiry.inquiryOutlets;
                for (var i = 0; i < $scope.reply.inquiryOutlets.length; i++) {
                    $scope.reply.inquiryOutlets[i].schedule = getBooleanArray("0_0_0_0_0_0_0");
                }
            }
        }

        function useDefaultDays(outletIndex) {
            $scope.reply.inquiryOutlets[outletIndex].schedule = getBooleanArray("0_0_0_0_0_0_0");
        }

        function validateDay(outletIndex, dayIndex) {
            if (!$scope.reply.inquiryOutlets[outletIndex].schedule[dayIndex]
                && getNumberOfTrue($scope.reply.inquiryOutlets[outletIndex].schedule) >= $scope.selectedInquiry.deliveryPerWeek) {
                $scope.reply.inquiryOutlets[outletIndex].schedule[dayIndex] = true;
            } else if(!$scope.reply.inquiryOutlets[outletIndex].schedule[dayIndex]) {
                $scope.reply.inquiryOutlets[outletIndex].schedule[dayIndex] = false;
            }
        }

        function discard() {
            $scope.reply = {};
            $scope.showReplyPanel = false;
        }

        function send() {
            toaster.pop('success', 'Success', 'Cool, time to create REST API');
            var temp = $scope.reply.inquiryOutlets;
            for (var i = 0; i < $scope.reply.inquiryOutlets.length; i++) {
                $scope.reply.inquiryOutlets[i].schedule = getStringOfArray($scope.reply.inquiryOutlets[i].schedule);
                console.log($scope.reply.inquiryOutlets[i].schedule);
            }
            $scope.reply.inquiryOutlets = temp;
        }

        function openEateryProfileModal() {
            $modal.open({
                templateUrl: 'myModalContent.html',
                controller: 'EateryProfileController',
                resolve: {
                    eatery: function () {
                        return $scope.selectedInquiry.eatery;
                    },
                    deps: ['$ocLazyLoad',
                        function ($ocLazyLoad) {
                            return $ocLazyLoad.load([
                                '/js/modals/eatery-profile.controller.js'
                            ]);
                        }
                    ]
                }
            });
        }

        function getBooleanArray(daysText) {
            return daysText.split('_').map(function (x) {
                return parseInt(x) != 0
            });
        }

        function getStringOfArray(array) {
            var tempArray = array.map(function (x) {
                return x ? 1 : 0
            });
            return tempArray.join('_');
        }

        function getNumberOfTrue(array) {
            var result = 0;
            for (var i = 0; i < array.length; i++) {
                if (array[i]) {
                    result++;
                }
            }
            return result;
        }
    }
})();
