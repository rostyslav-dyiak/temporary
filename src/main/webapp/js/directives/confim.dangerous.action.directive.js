/**
 * Created by rdyyak on 14.05.15.
 */
app.directive('ngConfirmClick', ['$ngBootbox',
    function ($ngBootbox) {
        return {
            link: function (scope, element, attr) {
                var msg = attr.ngConfirmClick || "Are you sure?";
                var clickAction = attr.confirmedClick;
                element.bind('click', function (event) {
                    $ngBootbox.confirm(msg)
                        .then(function () {
                            scope.$eval(clickAction);
                        }, function () {
                            console.log('Confirm dismissed!');
                        });
                });
            }
        };
    }])
