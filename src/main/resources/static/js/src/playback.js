rangeBar = document.getElementById("myRange");
document.getElementById("replay").addEventListener("click", replay);
selectTag = document.getElementById("visitorSelect");

/* Aggiungere nuovi elementi allla lista delle opzioni
d3.select("#visitorSelect")
    .append("option")
    .attr("value", "prova")
    .text("prova") */

var data;
var cx = 1000/2;
var cy = 600/2;
var paddingX = 100;
var paddingY = 50;
var POIs = [{"id":44, "x":68, "y":94, "name":"CarpenterTools"},{"id":21,"x":68, "y":143, "name":"Pottery"},{"id":46,"x":68, "y":176, "name":"MaritimeArcheology"},{"id":33,"x":155, "y":116, "name":"ShipBack"},
    {"id":50,"x":192, "y":150, "name":"ShipFront"},{"id":77,"x":215, "y":113, "name":"ShipBigScreen"},{"id":18,"x":234, "y":148, "name":"ShipEntrance"},{"id":78,"x":334, "y":65, "name":"StairsToBathroom"},
    {"id":55,"x":348, "y":160, "name":"GlassOvenVessels"},{"id":45,"x":414, "y":177, "name":"BronzeTools"},{"id":58,"x":395, "y":240, "name":"MosaicfromSynagogue"},{"id":52,"x":361, "y":253, "name":"StoneVesselsBowl"},
    {"id":32,"x":268, "y":266, "name":"WoodenTools"},{"id":59,"x":412, "y":283, "name":"Phoenicians"},{"id":43,"x":408, "y":313, "name":"MaterialCultures"},{"id":62,"x":439, "y":131, "name":"EntranceGallileeRebellion"},
    {"id":80,"x":439, "y":283, "name":"BottomStairs"},{"id":34,"x":323, "y":330, "name":"BuildingMethodsAndFacilities"},{"id":65,"x":334, "y":300, "name":"ReligionAndCult"},{"id":38,"x":361, "y":327, "name":"CraftsAndArts"},
    {"id":54,"x":355, "y":359, "name":"ImportedPottery"},{"id":37,"x":316, "y":354, "name":"MaritimeCommerce"},{"id":36,"x":276, "y":351, "name":"BurialTradition2"},{"id":20,"x":279, "y":318, "name":"PhoenicianWriting1"},
    {"id":66,"x":303, "y":300, "name":"EverydayPottery"},{"id":72,"x":485, "y":74, "name":"CanaaniteStelae"},{"id":47,"x":483, "y":105, "name":"DuckBoxIvories"},{"id":60,"x":558, "y":92, "name":"IvoryWomanPhoenician"},
    {"id":64,"x":584, "y":103, "name":"ClayAmphorae"},{"id":5,"x":546, "y":180, "name":"AnimalModels"},{"id":63,"x":480, "y":265, "name":"ChalcolitePeriod"},{"id":39,"x":533, "y":270, "name":"LeadCoffinMosaic"},
    {"id":49,"x":586, "y":279, "name":"ColumnNearCoffins"},{"id":40,"x":517, "y":301, "name":"JewishCoffins"},{"id":56,"x":490, "y":343, "name":"Anthropoids"},{"id":16,"x":473, "y":368, "name":"DecoratedDoors"},
    {"id":53,"x":535, "y":365, "name":"MenorahJewishEpigraphy"},{"id":2,"x":584, "y":360, "name":"RomanDivinitiesStatuettes"},{"id":74,"x":449, "y":421, "name":"JerusalemPhoto"},{"id":41,"x":517, "y":416, "name":"PersianCult"},
    {"id":4,"x":572, "y":416, "name":"SymbolsJewishMenorah"},{"id":51,"x":621, "y":382, "name":"EntranceReubenHecht"},{"id":76,"x":674, "y":63, "name":"Elevator2"},{"id":6,"x":732, "y":70, "name":"Gems"},
    {"id":70,"x":811, "y":102, "name":"Cyprus"},{"id":61,"x":830, "y":138, "name":"GreeceEgypt"},{"id":8,"x":685, "y":143, "name":"Weights"},{"id":57,"x":725, "y":125, "name":"TempleMount"},
    {"id":48,"x":773, "y":149, "name":"Jerusalem"},{"id":67,"x":664, "y":230, "name":"Coins"},{"id":71,"x":722, "y":213, "name":"SevenSpecies"},{"id":68,"x":821, "y":219, "name":"OilLamps"},
    {"id":3,"x":817, "y":260, "name":"UpperFloorEntrance"},{"id":79,"x":658, "y":402, "name":"MuseumMotto"}];
var POIsObject = {};
var startPoint = {"id":51,"x":621, "y":382, "name":"EntranceReubenHecht"};
var legendX = 35;
var legendY = 220;
var oScale = d3.scaleOrdinal(d3.schemePaired);      // colori per i dati
var radius = 7;
var speed = 1;

function listen() {
    rangeBar.addEventListener("change", changeSpeed(parseFloat(rangeBar.value)));
}

function changeSpeed(value) {
    speed = value/100;
    document.getElementById("speed").textContent=speed+"x";
    console.log("Speed: " + speed)
}

var result = document.getElementById("result");
            var mine = document.getElementById("myRange");
            function change(){
                result.innerText = mine.value/100;
            }

POIs.forEach(d=>POIsObject[d.id]={"x":d.x, "y":d.y, "name":d.name});

document.addEventListener('keydown', e =>
    // only call animate when no animation is currently ongoing, else increment
    e.code === "KeyA" ? count++ || animate() : 0
);

var svg = d3.select("div.svg-container")
   .append("div")
   // Container class to make it responsive.
   .classed("svg-container", true) 
   .append("svg")
   // Responsive SVG needs these 2 attributes and no w"id"th and height attr.
   .attr("preserveAspectRatio", "xMinYMin meet")
   .attr("viewBox", "0 0 1000 600")  // dimensioni 800x800 + 200 di padding x + 100 di padding y
   // Class to make it responsive.
   .classed("svg-content-responsive", true)
   .on("click", function() {console.log(d3.pointer(event,svg.node()));});   // funzione che mi permette di ottenere le coordinate relative del 
   
/* creazione della mappa */
svg.append("image")
    .attr("x", 50)
    .attr("y", 0)
    .attr("width", "80%")
    .attr("height", "80%")
    .attr("xlink:href", "/images/HechtMuseumNew_edited.png")
    .transition().duration(1000)
    .attr("opacity", 1);

/* Creazione dei vari POI */    
var g1 = svg.append("g") // gruppo di punti
    .attr("id","gpois");

var poi = g1.selectAll(".pois")
    .data(POIs);

poi.enter().append("circle")    // enter
    .attr("id", d=>"poi"+d.id)
    .attr("class", "pois")
    .attr("cx", d=>d.x)
    .attr("cy", d=>d.y)
    .attr("r", 3)
    .attr("stroke", "black")
    .attr("stroke-width", 1)
    .on("mouseover", handleMouseOver)
    .on("mouseout", handleMouseOut)
    .attr("opacity", 0);

function updatePoi() {
    d3.selectAll(".pois")
        .attr("r", 3)
        .attr("fill", "black")
        .attr("opacity", 0);
}

poi.exit().remove();    // exit

/* Create color scale */
function mapColors(data) {
    let strValue = [];
    data.forEach(function(d) {
        strValue.push("#poi"+d.id); // particolare tipo di hashing 
    })
    oScale.domain(strValue);
}

/* legenda luoghi visitati */
function doLegend(data) {

    let g = svg.append("g")
        .attr("class","legend");

    g.append("text")
        .attr("x",legendX)
        .attr("y", legendY)
        .text("Visit order:")
    let g1 = g.append("g");
    let g2 = g.append("g");
    let i = 0;
    data.forEach(function (d){
        i++;
        g1.append("text")
            .attr("class", "legend")
            .attr("id", "poi"+d.id)
            .attr("x", legendX+15)
            .attr("y", legendY+(20*i))
            .text(POIsObject[d.id].name)
            .on("mouseover", handleMouseOver)
            .on("mouseout", handleMouseOut);

        g2.append("circle")
            .attr("id", "poi"+d.id)
            .attr("class", "legend")
            .attr("cx", legendX)
            .attr("cy", (legendY+(20*i)-5))
            .attr("r", 5)
            .attr("stroke", "black")
            .attr("stroke-width", 1)
            .on("mouseover", handleMouseOver)
            .on("mouseout", handleMouseOut);
    });
    let legendBox = g.node().getBBox();
    let padding = 5;
    g.append("rect")
        .attr("class", "legend")
        .attr("x", legendBox.x-padding)
        .attr("y", legendBox.y-padding)
        .attr("width", legendBox.width+(2*padding))
        .attr("height", legendBox.height+(2*padding))
        .attr("fill", "none")
        .attr("stroke", "gray")
        .attr("border-radius", 25);

    g.transition().duration(1000).attr("opacity",1);
}

function handleMouseOver(d, i) {
    let idPoint = "#"+d3.select(this).attr('id');       // id dei punti

    let points = d3.selectAll("circle"+idPoint);    // metto in primo piano il gruppo di punti
    // Selezione del gruppo di punti appartenenti allo stesso poligono
    points.raise()
        .transition()
        .attr("r", radius * 2);
}

// Evento quando mouse va via dal punto
function handleMouseOut(d, i) {
    let idPoint = "#"+d3.select(this).attr('id');

    // Selezione del gruppo di punti appartenenti allo stesso poligono
    d3.selectAll(idPoint)
        .transition()
        .attr("r", radius);
  }

/* creazione visitatore */
function newVisitor() {
    i=1;
    svg.select("text#label")
        .text("");

    svg.append("circle")    // enter
        .attr("class", "visitor")
        .attr("cx", startPoint.x)
        .attr("cy", startPoint.y)
        .attr("r", 30)
        .transition().duration(1500)
        .attr("r", 7)
        .attr("fill","green")
        .on("end", () => {animate()});
}

function removeVisitors() {
    svg.selectAll("circle.visitor").interrupt().remove();
    svg.selectAll(".legend").remove();
}

textY = 485
/* label della posizione del visitatore */    
var visitorLabel = svg.append("g")
    .attr("id", "glabel")
    .append("text")
    .attr("class", "visitor")
    .attr("id", "label")
    .attr("x", cx)
    .attr("y", textY)
    .text("")
    .style("text-anchor", "middle")

/* funzione che mostra il percorso del visitatore */    
function moveVisitor(data, count) {
    
    svg.select("#label")
        .text(POIsObject[data[0].id].name);

    svg.selectAll("circle#poi"+data[0].id)
        .attr("opacity", 1)
        .transition()
        .attr("r", radius)
        .attr("fill",oScale("#poi"+data[0].id));

    svg.select("circle.visitor")
        .transition()
        .duration(1500/speed) // <--- reduce the duration when count is high
        .delay((data[0].time*10)/speed)
        .attr("cx", POIsObject[data[0].id].x)
        .attr("cy", POIsObject[data[0].id].y)
        .on("end", () => {
            // when all objects stopped animating and more animate() calls needed:
            if (--count > 0) {
                moveVisitor(data.slice(1,data.lenght), count); // on to the next...
            }
            else {
                svg.select("circle.visitor")    // at the end of the visit
                    .transition()
                    .duration(1500/speed)
                    .delay(1000/speed)
                    .remove();
            }
        });
}

// import dei dati dal file json
function animate() {
    console.log(selectTag)
    // Qui bisogna restituire la lista di oggetti delle posizioni
    d3.json("data/"+selectTag.value+".json").then(function(d) {
        console.log(d);
        data = d;

        doLegend(data);
        mapColors(data);

        moveVisitor(data, data.length); 
    }).catch(function(error){
        console.log(error)                  // log in caso di errore
    });
}

function replay() {
    updatePoi();
    removeVisitors()
    newVisitor();
}

// aggiungere sistema (barra) per aumentare (o ridurre) la velocità di transizione delle animazioni
// controllare perché non prende il nuovo file json 