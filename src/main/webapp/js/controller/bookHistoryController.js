app.controller('bookHistoryController', ['$scope', '$location', '$cookies', function ($scope, $location, $cookies) {

    if($cookies.get('memberId') === undefined) {
        disconnect();

    } else {
        bindSelectChange();

        var bookHistory = new BookHistory();
        let searchBookHistoryPromise = bookHistory.getListOrderId($cookies.get('memberId'));
        searchBookHistoryPromise.then((response) => {
            if(response instanceof Array) {
                $scope.searchHistories = response;
                $scope.$apply();

            } else {
                disconnect();
                $scope.$apply();
            }
        }, (error) => {
            console.error('Failed!', error);
        });

        $scope.delete = () => {
            if (confirm("선택한 검색 히스토리를 삭제하시겠습니까?") == true) {
                if(bookHistory.validateDeleteList()) {
                    let searchBookHistoryPromise = bookHistory.deleteList($cookies.get('memberId'));
                    searchBookHistoryPromise.then((response) => {
                        if (response instanceof Array) {
                            $scope.searchHistories = response;
                            $scope.$apply();

                        } else {
                            disconnect();
                            $scope.$apply();
                        }

                    }, (error) => {
                        console.error('Failed!', error);
                    });

                } else {
                    alert("선택된 검색 히스토리가 없습니다.")
                }
            } else {
                return;
            }
        };

        $scope.back = () => {
            $location.path('/search');
        };
    }

    function bindSelectChange() {
        $('#sort').on('change', (element) => {
            let sort = element.target.value;

            if('query' == sort) {
                let searchBookHistoryPromise = bookHistory.getListOrderQuery($cookies.get('memberId'));
                searchBookHistoryPromise.then((response) => {
                    if(response instanceof Array) {
                        $scope.searchHistories = response;
                        $scope.$apply();

                    } else {
                        disconnect();
                        $scope.$apply();
                    }
                }, (error) => {
                    console.error('Failed!', error);
                });

            } else if('datetime' == sort) {
                let searchBookHistoryPromise = bookHistory.getListOrderDatetime($cookies.get('memberId'));
                searchBookHistoryPromise.then((response) => {
                    if(response instanceof Array) {
                        $scope.searchHistories = response;
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

    function disconnect() {
        if($cookies.get('memberId') !== undefined && $cookies.get('memberName') !== undefined) {
            $cookies.remove('memberId');
            $cookies.remove('memberName');
        }
        alert('세션이 종료되었습니다. 로그인 페이지로 돌아갑니다.');
        $location.path('/');
    }
}]);