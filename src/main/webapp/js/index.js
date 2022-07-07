const wsURI = `ws://${document.location.host + document.location.pathname}chat`

const btnSend = document.getElementById("btnSend");
const btnEnterChat = document.getElementById("btnEnterChat");
const display = document.getElementById("display");
const chat = document.getElementById("chat");
const main = document.getElementById("main");
const inputMessage = document.getElementById("message");
const inputUserName = document.getElementById("userName");
const btnDisconnect = document.getElementById("btnDisconnect");
const inputFile = document.getElementById("file");
var pathFile = undefined;
var fileType = undefined;

chat.style.display = "none";
btnSend.addEventListener("click", send);
btnEnterChat.addEventListener("click", enterChat);
btnDisconnect.addEventListener("click", diconnect);

const webSocket = new WebSocket(wsURI);
webSocket.onmessage = onMessage;
webSocket.onopen = onOpen;

function onOpen() {
    console.log("Open connection: " + wsURI);
}

function onClose() {
    console.log("Close connection: " + wsURI);
}

function onMessage(e) {
    console.log(e);
    addMessageToDisplay(e.data);
}

function send(e) {
    e.preventDefault();
    const message = inputMessage.value;
    const userName = inputUserName.value;
    var dataJson = { message, userName };

    if (validateField(userName) && validateField(message)) {
        if (inputFile.files.length > 0) {
            const readerFile = handleFile();
            readerFile.onloadend = function () {
                //dataJson = {...dataJson, file: readerFile.result};
                //console.log(dataJson);
                pathFile = readerFile.result;
                webSocket.send(JSON.stringify(dataJson));
            };
        } else {
            //console.log(dataJson);
            webSocket.send(JSON.stringify(dataJson));
        }
    } else {
        alert("Ingrese un mensaje");
    }
}

function addMessageToDisplay(dataString) {
    const data = JSON.parse(dataString);
    const content = `<p>
        <span>${data.userName}:</span> ${data.message}
    </p>
    ${pathFile !== undefined ? `
        <p>
            <embed src="${pathFile}" type="${fileType}" width="200" id="visual">
        </p>
    ` : ""}
    `;
    document.getElementById("display").innerHTML += content;
    inputMessage.value = "";
    pathFile = undefined;
    fileType = undefined;
}

function enterChat(e) {
    e.preventDefault();
    if (validateField(inputUserName.value)) {
        chat.style.display = "block";
        main.style.display = "none";
    } else {
        alert("Ingrese un nombre válido");
    }
}

function handleFile() {
    const myFile = inputFile.files[0];
    const oMyBlob = new Blob([myFile], {type: myFile.type}); // the blob
    const reader = new FileReader();
    reader.readAsDataURL(oMyBlob);
    fileType = myFile.type;
    return reader;
}

function diconnect() {
    window.location.reload();
}

function validateField(value) {
    return value !== "" && value.length > 3;
}
