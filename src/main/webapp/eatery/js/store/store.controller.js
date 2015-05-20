(function () {
    'use strict';

    app.controller('StoreController', StoreController);

    StoreController
        .$inject = [
        '$scope',
        'toaster',
        'OutletsFactory',
        'InquiryFactory'
    ];

    function StoreController($scope, toaster, OutletsFactory, InquiryFactory) {
        var outletIds = [];

        $scope.searchQuery = '';
        $scope.cartDialogOpened = false;
        $scope.cartItems = 0;
        $scope.cartInquiries = [];
        $scope.outlets = [];

        $scope.toggleDialog = toggleDialog;
        $scope.search = search;
        $scope.addToCart = addToCart;
        $scope.toggleOutlet = toggleOutlet;
        $scope.removeProduct = removeProduct;
        $scope.deleteInquiry = deleteInquiry;
        $scope.sent = sent;

        activate();

        function activate() {
            OutletsFactory.query({},
                function (data) {
                    $scope.outlets = data;
                    outletIds = getIdArray($scope.outlets);
                }, function (e) {
                    console.error(e);
                    toaster.pop('error', 'Error', 'Error in initialization');
                });
        }

        function search() {
            // $scope.searchQuery
            console.log("Sending request... " + $scope.searchQuery);
        }

        function toggleDialog() {
            $scope.cartDialogOpened = $scope.cartDialogOpened ? false : true;
        }

        function addToCart(supplier, product) {
            var index = getIdArray($scope.cartInquiries).indexOf(supplier.id);

            if (index < 0) {
                $scope.cartInquiries.push({
                    id: supplier.id,
                    supplier: supplier
                });
                index = $scope.cartInquiries.length - 1;
            }
            if (!$scope.cartInquiries[index].products) {
                $scope.cartInquiries[index].products = [];
            }
            if (containObjectIndex($scope.cartInquiries[index].products, product) < 0) {
                $scope.cartInquiries[index].products.push(product);
                $scope.cartInquiries[index].outlets = outletIds;
                getProductCount();
            }
            console.log($scope.cartInquiries);
        }

        function toggleOutlet(inquiry, outlet) {
            var index = $scope.cartInquiries[inquiry.id].outlets.indexOf(outlet.id);
            if (index > -1) {
                $scope.cartInquiries[inquiry.id].outlets.splice(index, 1);
            } else {
                $scope.cartInquiries[inquiry.id].outlets.push(outlet.id);
            }
        }

        function removeProduct(inquiry, product) {
            $scope.cartInquiries[inquiry.id].products.splice(containObjectIndex($scope.cartInquiries[inquiry.id].products, product), 1);
            getProductCount();
        }

        function deleteInquiry(inquiry) {
            $scope.cartInquiries.splice(containObjectIndex($scope.cartInquiries, inquiry), 1);
            toaster.pop('success', 'Success', 'Inquiry has been deleted');
            activate();
            getProductCount();
        }

        function sent(inquiry) {
            InquiryFactory.save(inquiry,
                function () {
                    toaster.pop('success', 'Success', 'Inquiry has been sent');
                }, function (e) {
                    console.error(e);
                    toaster.pop('error', 'Error', 'Please try again');
                })

        }



        function containObjectIndex(array, object) {
            var result = -1;
            for (var i = 0; i < array.length; i++) {
                if (array[i].id == object.id) {
                    result = i;
                }
            }
            return result;
        }

        function getIdArray(array) {
            var result = [];
            for (var i = 0; i < array.length; i++) {
                result.push(array[i].id);
            }
            return result;
        }

        function getProductCount() {
            var result = 0;
            for (var i = 0; i < $scope.cartInquiries.length; i++) {
                result += $scope.cartInquiries[i].products.length;
            }
            $scope.cartItems = result;
        }
    }
})();
