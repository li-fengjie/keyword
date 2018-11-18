/**
 * ajax 封装
 * @returns {*}
 */

function createXMLHttpRequest() {
    try{
        return new createXMLHttpRequest();
    }catch (e){
        try {
            return ActiveXObject("Msxml2.XMLHTTP");//IE 6
        }catch (e){
            try {
                return ActiveXObject("Microsoft.XMLHTTP"); //IE 5
            }catch (e){
                throw e;
            }
        }
    }
}

/**
 * ajax
 * @param options
 *  method 默认get
 *  asyn 默认 true
 *  params 请求体
 *  url
 *  callback 回调函数
 */
function ajax(options) {
    var xmlHttp = createXMLHttpRequest();
    if(!options.method){
        options.method = "GET";
    }
    if(options.asyn == undefined){
        options.asyn = true;
    }
    xmlHttp(options.method,options.url,options.asyn);

    if(options.method == "GET" || options.method == "get"){
        xmlHttp.open("GET");
    }
    if(options.method == "POST" || options.method == "post"){
        xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    }
    xmlHttp.send(options.params);
    xmlHttp.onreadystatechange = function () {
        if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
            var data;
            if(options.type == "xml"){
                data = xmlHttp.responseText;
            }else if(options.type == "json"){
                var text = xmlHttp.responseText;
                data = eval("(" + text + ")");
            }else {
                data = xmlHttp.responseText;
            }

            options.callback(data);
        }
    }
}
