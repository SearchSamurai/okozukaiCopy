/**
 * 指定したページへアクションは動かさずに遷移する。
 * @param linkTgt リンク先
 */
function move(linkTgt) {
	location.href = linkTgt;
}

/**
 * 項目に設定されたvalue値をクリアする。
 */
function clearFormAll() {
    for (var i=0; i<document.forms.length; ++i) {
        clearForm(document.forms[i]);
    }
}
function clearForm(form) {
    for(var i=0; i<form.elements.length; ++i) {
        clearElement(form.elements[i]);
    }
}
function clearElement(element) {
    switch(element.type) {
        case "hidden":
        case "submit":
        case "reset":
        case "button":
        case "image":
            return;
        case "file":
            return;
        case "text":
        case "password":
        case "textarea":
        case "option":
        case "select-one":
            element.value = "";
            return;
        case "checkbox":
        case "radio":
            element.checked = false;
            return;
        case "select-multiple":
            element.selectedIndex = 0;
            return;
        default:
    }
}

///**
//* 桁不足分のゼロパディングを行う。
//* @param formObj
//*/
//function padDate(formObj) {
//	var year = formObj.elements[0].value;
//	var month = formObj.elements[1].value;
//	var day = formObj.elements[2].value;
//
//	if (year != "" && year.length < 4) {
//		formObj.elements[0].value = ("000"+ year).slice(-4);
//	}
//	if (month != "" && month.length < 2) {
//		formObj.elements[1].value = ("0"+ month).slice(-2);
//	}
//	if (day != "" && day.length < 2) {
//		formObj.elements[2].value = ("0"+ day).slice(-2);
//	}
//}
