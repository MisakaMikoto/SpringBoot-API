class Signin {
    constructor() {}

    createAccount(params) {
        let commonRequestPromise = new CommonRequestPromise();
        return commonRequestPromise.post('/v1/account/signin', params).then((response) => {
            return JSON.parse(response);

        }, function(error) {
            console.error("Failed!", error);
        });
    }

    validLogin() {
        if($('#query').val().length == "") {
            alert("검색어를 입력해 주세요.");
            return false;

        } else if($( "#size option:selected" ).val() == "0") {
            alert("페이지당 검색 개수를 선택해 주세요.");
            return false;

        } else if($( "#sort option:selected" ).val() == "none") {
            alert("검색의 정렬 조건을 선택해 주세요.");
            return false;

        } else {
            return true;
        }
    }
}