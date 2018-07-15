app.controller('bookmarkController', ['$scope', '$location', '$cookies', function ($scope, $location, $cookies) {
    if($cookies.get('memberId') === undefined) {
        disconnect();

    } else {
        bindSelectChange();

        var bookmark = new Bookmarks();
        let bookmarkPromise = bookmark.getListOrderId($cookies.get('memberId'));
        bookmarkPromise.then((response) => {
            if (response instanceof Array) {
                $scope.searchBookmarks = response;
                $scope.$apply();

            } else {
                disconnect();
                $scope.$apply();
            }
        }, (error) => {
            console.error('Failed!', error);
        });

        $scope.back = () => {
            $location.path('/search');
        };
    }

    function bindSelectChange() {
        $('#sort').on('change', (element) => {
            let sort = element.target.value;

            if('title' == sort) {
                let searchBookmarkPromise = bookmark.getListOrderTitle($cookies.get('memberId'));
                searchBookmarkPromise.then((response) => {
                    if(response instanceof Array) {
                        $scope.searchBookmarks = response;
                        $scope.$apply();

                    } else {
                        disconnect();
                        $scope.$apply();
                    }
                }, (error) => {
                    console.error('Failed!', error);
                });

            } else if('status' == sort) {
                let searchBookmarkPromise = bookmark.getListOrderStatus($cookies.get('memberId'));
                searchBookmarkPromise.then((response) => {
                    if (response instanceof Array) {
                        $scope.searchBookmarks = response;
                        $scope.$apply();

                    } else {
                        disconnect();
                        $scope.$apply();
                    }
                }, (error) => {
                    console.error('Failed!', error);
                });

            } else if('salePrice' == sort) {
                let searchBookmarkPromise = bookmark.getListOrderSalePrice($cookies.get('memberId'));
                searchBookmarkPromise.then((response) => {
                    if (response instanceof Array) {
                        $scope.searchBookmarks = response;
                        $scope.$apply();

                    } else {
                        disconnect();
                        $scope.$apply();
                    }
                }, (error) => {
                    console.error('Failed!', error);
                });

            } else {
                // another case..
                ;
            }
        });
    }

    $scope.delete = () => {
        if (confirm("선택한 북마크를 삭제하시겠습니까?") == true) {
            if(bookmark.validateDeleteList()) {
                let searchBookmarkPromise = bookmark.deleteList($cookies.get('memberId'));
                searchBookmarkPromise.then((response) => {
                    if (response instanceof Array) {
                        $scope.searchBookmarks = response;
                        $scope.$apply();

                    } else {
                        disconnect();
                        $scope.$apply();
                    }

                }, (error) => {
                    console.error('Failed!', error);
                });

            } else {
                alert("선택한 북마크가 없습니다.");
            }

        } else {
            return;
        }
    };

    function disconnect() {
        if($cookies.get('memberId') !== undefined && $cookies.get('memberName') !== undefined) {
            $cookies.remove('memberId');
            $cookies.remove('memberName');
        }
        alert('세션이 종료되었습니다. 로그인 페이지로 돌아갑니다.');
        $location.path('/');
    }
}]);