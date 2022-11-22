//used to display network
const createDeviceButton = document.getElementById("createDeviceButton");

anychart.data.loadJsonFile('dataset.json', function (data) {
    // create a chart from the loaded data
    var chart = anychart.graph(data);

    // set the title
    chart.title("Network Graph");

    //enable labels of nodes
    chart.nodes().labels().enabled(true);
    chart.edges().labels().enabled(true);

    //configure labels of nodes
    chart.nodes().labels().format("{%id}");
    chart.nodes().labels().fontSize(12);
    chart.nodes().labels().fontWeight(600);

    //configures labels of edges
    chart.edges().labels().format("{%weight}");
    chart.edges().labels().fontSize(12);
    chart.edges().labels().fontWeight(600);

    //configure labels of nodes
    chart.group("router").labels().fontColor("#00bfa5");
    // draw the chart
    chart.container("container").draw();
})

function createDevice(){
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", theUrl, false ); // false for synchronous request
    xmlHttp.send( null );

}

createDeviceButton.addEventListener('click', createDevice);