class Modify {
    checkPassword(params) {
        let commonRequestPromise = new CommonRequestPromise();
        return commonRequestPromise.put('/v1/account/modify', params).then((response) => {
            return JSON.parse(response);

        }, function(error) {
            console.error("Failed!", error);
        });
    }

    validateModify() {
        if($('#id').val().length == "") {
            alert("아이디를 입력해 주세요.");
            return false;

        } else if($('#password' ).val() == "") {
            alert("암호를 입력해 주세요.");
            return false;

        } else if($('#modifyPassword' ).val() == "") {
            alert("수정할 암호를 입력해 주세요.");
            return false;

        } else if($('#password' ).val() == $('#modifyPassword' ).val()) {
            alert("기존의 암호와 수정할 암호는 같을 수 없습니다.");
            return false;

        } else {
            return true;
        }
    }
}