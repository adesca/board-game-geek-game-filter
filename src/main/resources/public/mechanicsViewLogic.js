let currentMechanics = {};

// setup mechanics on start
fetchForJson('/api/mechanics/all')
    .then(function (mechanics) {
        var containerDiv = document.getElementById('mechanics-container');

        mechanics.map(function (mechanic) {
            currentMechanics[mechanic.value] = mechanic;

            // IDs have to start with letters
            // https://stackoverflow.com/questions/20306204/using-queryselector-with-ids-that-are-numbers
            // https://stackoverflow.com/a/34777644

            const mechanicElement = htmlToElement(`<div>
                <input type="checkbox" id="m${mechanic.id}">
                <label for="m${mechanic.id}">${mechanic.value}</label>
            </div>`)
            containerDiv.appendChild(mechanicElement);
        });

        // containerDiv.appendChild()
    });

updateMechanicsView = function (mechanicsArr) {
    document.querySelectorAll('input[type="checkbox"]').forEach(checkbox => {
        checkbox.checked = false
    });

    mechanicsArr.forEach((mechanic) => {
        document.querySelector(`input#m${currentMechanics[mechanic].id}`).checked = true;
    })
};

