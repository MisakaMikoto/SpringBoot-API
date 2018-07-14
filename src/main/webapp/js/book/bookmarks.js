class Bookmarks {
    add(memberId) {
        let params = {
            title: $('#title').text().trim(),
            url: $('#url').text().trim(),
            thumbnail: $('#thumbnail').find('img').attr('ng-src').trim(),
            price: $('#price').text().trim(),
            salePrice: $('#salePrice').text().trim(),
            status: $('#status').text().trim(),
            isbn: $('#isbn').text().trim()
        };

        let commonRequestPromise = new CommonRequestPromise();
        return commonRequestPromise.post('/v1/bookmark/' + memberId, params).then((response) => {
            return JSON.parse(response);

        }, function(error) {
            console.error("Failed!", error);
        });
    }

    getListOrderId(memberId) {
        let commonRequestPromise = new CommonRequestPromise();
        return commonRequestPromise.get('/v1/bookmark/' + memberId + '/order/id').then((response) => {
            return JSON.parse(response);

        }, function(error) {
            console.error('Failed!', error);
        });
    }

    getListOrderTitle(memberId) {
        let commonRequestPromise = new CommonRequestPromise();
        return commonRequestPromise.get('/v1/bookmark/' + memberId + '/order/title').then((response) => {
            return JSON.parse(response);

        }, function(error) {
            console.error('Failed!', error);
        });
    }

    getListOrderStatus(memberId) {
        let commonRequestPromise = new CommonRequestPromise();
        return commonRequestPromise.get('/v1/bookmark/' + memberId + '/order/status').then((response) => {
            return JSON.parse(response);

        }, function(error) {
            console.error('Failed!', error);
        });
    }

    getListToIsbn(memberId, isbn) {
        let commonRequestPromise = new CommonRequestPromise();
        return commonRequestPromise.get('/v1/bookmark/' + memberId + '/' + isbn).then((response) => {
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
        return commonRequestPromise.delete('/v1/bookmark/' + memberId + '?ids=' + ids).then((response) => {
            return JSON.parse(response);

        }, function(error) {
            console.error('Failed!', error);
        });
    }
}