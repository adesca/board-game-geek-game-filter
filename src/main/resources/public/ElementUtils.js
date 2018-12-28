function htmlToElement(html) {
    var template = document.createElement('template');
    html = html.trim(); // Never return a text node of whitespace as the result
    template.innerHTML = html;
    // debugger;
    return template.content.firstChild;
}

// https://stackoverflow.com/questions/494143/creating-a-new-dom-element-from-an-html-string-using-built-in-dom-methods-or-pro/35385518#35385518

const fillTemplate = function (templateString, templateVars) {
    return new Function("return `" + templateString + "`;").call(templateVars);
}

const templateCache = {};
const interpolateRemoteTemplate = (location, context) => {

    let templatePromise;
    if (templateCache[location]) {
        templatePromise = Promise.resolve(templateCache[location]);
    } else {
        templatePromise = fetch(location)
            .then(response => {
                return response.text()
            })
    }

    return templatePromise.then(templateString => {
        templateCache[location] = templateString;
        return fillTemplate(templateString, context);
    })

}

const removeChildrenElements = (parentEl) => {

    while (parentEl.firstChild) {
        parentEl.removeChild(parentEl.firstChild);
    }
}