class BookHistory {
    getListOrderId(memberId) {
        let commonRequestPromise = new CommonRequestPromise();
        return commonRequestPromise.get('/v1/history/' + memberId + '/order/id').then((response) => {
            return JSON.parse(response);

        }, function(error) {
            console.error('Failed!', error);
        });
    }

    getListOrderQuery(memberId) {
        let commonRequestPromise = new CommonRequestPromise();
        return commonRequestPromise.get('/v1/history/' + memberId + '/order/query').then((response) => {
            return JSON.parse(response);

        }, function(error) {
            console.error('Failed!', error);
        });
    }

    getListOrderDatetime(memberId) {
        let commonRequestPromise = new CommonRequestPromise();
        return commonRequestPromise.get('/v1/history/' + memberId + '/order/datetime').then((response) => {
            return JSON.parse(response);

        }, function(error) {
            console.error('Failed!', error);
        });
    }

    deleteList(memberId) {
        let ids = "";
        $('input[type=checkbox]:checked').each( (index, element) => {
            if(index == 0) {
                ids += $(element).attr('id');
            } else {
                ids += "," + $(element).attr('id');
            }
        });

        let commonRequestPromise = new CommonRequestPromise();
        return commonRequestPromise.delete('/v1/history/' + memberId + '?ids=' + ids).then((response) => {
            return JSON.parse(response);

        }, function(error) {
            console.error('Failed!', error);
        });
    }

    validateDeleteList() {
        if($('input[type=checkbox]:checked').length > 0) {
            return true;

        }  else {
            return false;
        }
    }
}