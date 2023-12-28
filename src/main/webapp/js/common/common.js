/**
 * ajax post
 * @param settings
 */
function gnAjaxPost(settings) {

    let async = settings.async || false;
    let dataType = settings.dataType || 'json';
    let contentType = settings.contentType || 'application/json';

    let url = _contextPath + settings.url;
    let data = settings.data;

    let success = settings.success;
    let error = settings.error;
    let beforeSend = settings.beforeSend;
    let complete = settings.complete;


    $.ajax({

        type: 'POST',
        async: async,
        url: url,
        dataType: dataType,
        data: data,
        contentType: contentType,
        beforeSend: beforeSend || function () {
            //console.log('ajaxPost :: beforeSend called.');
        },
        success: success || function (data) {
            //console.log('ajaxPost :: success called.');
        },
        error: error || function (xhr) {
            alert("status : " + xhr.responseJSON.status + "\nmessage : " + xhr.responseJSON.message);
        },
        complete: complete || function (xhr) {
            // console.log('ajaxPost :: complete called.');
        }

    });

}