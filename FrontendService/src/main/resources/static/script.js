document.addEventListener("DOMContentLoaded", function () {

    const stockForm = document.getElementById("stripe-login");
    const reduceStockForm = document.getElementById("stripe-login2");
    const showStockForm = document.getElementById("stripe-login3");
    const placeOrderForm = document.getElementById("stripe-login4");
    const showOrderForm = document.getElementById("stripe-login5");
    const addCartForm = document.getElementById("stripe-login6");
    const stockNameOptions = document.getElementById("stockNameOptions");
    const stockIdInput = document.getElementById("stockIdInput");
    const tableBody = document.getElementById("tableBody");
    const nameElement = document.getElementById('name');
    const emailElement = document.getElementById('email');
    const timeStartedElement = document.getElementById('time-started');
    const endSessionButton = document.getElementById('end-session');
    const startSessionButton = document.getElementById('start-session');

    let flag = true;

    // Sending Button Clicked Info to a function which will send to the kafka producer
    document.getElementById('home').addEventListener('click', () => {
        interaction = 'Home Page';
        sendUserInteractionToServer(interaction);
    });
    document.getElementById('addStocks').addEventListener('click', () => {
        interaction = 'Add Stocks';
        sendUserInteractionToServer(interaction);
    });
    document.getElementById('reduceStocks').addEventListener('click', () => {
        interaction = 'Reduce Stocks';
        sendUserInteractionToServer(interaction);
    });
    document.getElementById('showStocks').addEventListener('click', () => {
        interaction = 'Show Stocks';
        sendUserInteractionToServer(interaction);
    });


    document.getElementById('addCart').addEventListener('click', () => {
        interaction = 'Add Cart';
        sendUserInteractionToServer(interaction);
    });
    document.getElementById('placeOrder').addEventListener('click', () => {
        interaction = 'Place Order';
        sendUserInteractionToServer(interaction);
    });
    document.getElementById('showOrder').addEventListener('click', () => {
        interaction = 'Show Order';
        sendUserInteractionToServer(interaction);
    });



    function sendUserInteractionToServer(interaction) {
        // Make an HTTP POST request to your backend service
        fetch('http://localhost:8086/journey', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: interaction,//plain string body
        })
            .then(data => {
                console.log('Interaction sent to server:', interaction);
            })
            .catch(error => {
                console.error('Error sending interaction to server:', error);
            });
    }

    const startTimeKey = 'startTime';
    const updateTimeKey = 'updateTime';

    // Check if there is a start time stored in localStorage
    let startTime = localStorage.getItem(startTimeKey);

    // Initialize updateTime as true by default or use the stored value
    let updateTime = JSON.parse(localStorage.getItem(updateTimeKey)) !== false;

    // Function to update the elapsed time
    function updateTimeElapsed() {
        if (updateTime && startTime) {
            const currentTime = new Date();
            const elapsedMilliseconds = currentTime - new Date(startTime);
            const elapsedSeconds = Math.floor(elapsedMilliseconds / 1000);

            timeStartedElement.textContent = `Time Started: ${elapsedSeconds} seconds ago`;
        }

        setTimeout(updateTimeElapsed, 1000);
    }

    if (isSessionActive()) {
        document.getElementById('end-session-li').style.display = 'block';
        document.getElementById('start-session-li').style.display = 'none';
        updateTimeElapsed();
        console.log('Session is active');
    } else {
        document.getElementById('end-session-li').style.display = 'none';
        document.getElementById('start-session-li').style.display = 'block';
        console.log('Session is not active');
    }


    // Function to check if a session is active
    function isSessionActive() {
        return localStorage.getItem('sessionActive') === 'true';
    }

    // Button To Start Session
    startSessionButton.addEventListener('click', () => {
        startSession();
    });
    function handleAcceptClickStart() {
        interaction = 'Yes Button';
        console.log("idhar aya hu");
        sendUserInteractionToServer(interaction);

        const confirmDialog = document.getElementById('BlockUIConfirm');

        document.getElementById('end-session-li').style.display = 'block';
        document.getElementById('start-session-li').style.display = 'none';
        startTime = new Date(); // Set a new start time
        localStorage.setItem(startTimeKey, startTime); // Saving start time in local storage
        updateTime = true; // Start updating time when the button is pressed
        updateTimeElapsed();
        confirmDialog.style.display = 'none'; // Hide the confirmation dialog
        document.getElementById('confirmSessionBtn').removeEventListener('click', handleAcceptClickStart);

        }

    function handleAcceptClickStartPdf() {

            const confirmDialogPdf = document.getElementById('BlockUIConfirmPdf');

                    // Make an HTTP POST request to your backend service
                    fetch('http://localhost:8084/users/generatePdf', {
                        method: 'GET',
                        headers: {
                            'Content-Type': 'application/json',
                        },
                    })
                        .then(response => {
                            if (!response.ok) {
                                throw new Error('Network response was not ok');
                            }
                            return response.blob(); // Convert the response to a Blob
                        })
                        .then(blob => {
                            // Create a Blob URL for the binary data
                            const blobUrl = URL.createObjectURL(blob);

                            // Create a download link
                            const downloadLink = document.createElement('a');
                            downloadLink.href = blobUrl;
                            downloadLink.download = 'myJourney.pdf'; // Specify the desired filename
                            downloadLink.style.display = 'none'; // Hide the link

                            // Trigger a click event on the download link
                            document.body.appendChild(downloadLink);
                            downloadLink.click();

                            // Clean up by revoking the Blob URL
                            URL.revokeObjectURL(blobUrl);

                            console.log('PDF download initiated');
                        })
                        .catch(error => {
                            console.error('Error downloading PDF:', error);
                        });

            confirmDialogPdf.style.display = 'none'; // Hide the confirmation dialog
            document.getElementById('confirmSessionBtn').removeEventListener('click', handleAcceptClickStartPdf);

            }


    function handleAcceptClickEnd() {
            interaction = 'Yes Button';
            sendUserInteractionToServer(interaction);

            const confirmDialog = document.getElementById('BlockUIConfirm');
            const confirmDialogPdf = document.getElementById('BlockUIConfirmPdf');

            document.getElementById('end-session-li').style.display = 'none';
            document.getElementById('start-session-li').style.display = 'block';
            updateTime = false; // Stop updating time when the button is pressed
            localStorage.removeItem(startTimeKey); // Clear the stored startTime
            confirmDialog.style.display = 'none'; // Hide the confirmation dialog

            // show The Pdf Dialog
            confirmDialogPdf.style.display = 'block';
            document.getElementById('confirmSessionBtnPdf').addEventListener('click', handleAcceptClickStartPdf);//if clicked yes
            document.getElementById('cancelButtonPdf').addEventListener('click', () => {
                        confirmDialogPdf.style.display = 'none';
                    });
            document.getElementById('confirmSessionBtn').removeEventListener('click', handleAcceptClickEnd);
            }



    // Button To End Session
    endSessionButton.addEventListener('click', () => {
        endSession();
    });

    // Function to start a new session
    function startSession() {
        // Check if the session is already active
        if (localStorage.getItem('sessionActive') === 'true') {
            console.log('Session is already active.');
            return;
        }
        localStorage.setItem('sessionActive', 'true');

        interaction = 'Start Session';
        sendUserInteractionToServer(interaction);


        console.log("in the start session dialog");
        // Display the confirmation dialog
        const confirmDialog = document.getElementById('BlockUIConfirm');
        confirmDialog.style.display = 'block';

        // When the user clicks "Yes, Accept," perform the start session actions
        document.getElementById('confirmSessionBtn').addEventListener('click', handleAcceptClickStart);

        document.getElementById('cancelButton').addEventListener('click', () => {
            interaction = 'Cancel Button';
            sendUserInteractionToServer(interaction);
            confirmDialog.style.display = 'none';
        });

        console.log('Session started');
    }

    // Function to end the session
    function endSession() {
        localStorage.removeItem('sessionActive');
        localStorage.removeItem('sessionStartTime');

        interaction = 'End Session';
        sendUserInteractionToServer(interaction);


        console.log("in the end session dialog");
        // Display the confirmation dialog
        const confirmDialog = document.getElementById('BlockUIConfirm');
        confirmDialog.style.display = 'block';
        flag=false;
        // When the user clicks "Yes, Accept," perform the end session actions
        document.getElementById('confirmSessionBtn').addEventListener('click', handleAcceptClickEnd);

        document.getElementById('cancelButton').addEventListener('click', () => {
            interaction = 'Cancel Button';
            sendUserInteractionToServer(interaction);

            confirmDialog.style.display = 'none';
        });

        console.log('Session ended');
    }




    fetch("http://localhost:8084/users/show")
        .then(response => response.json())
        .then(userData => {
            nameElement.textContent = `${userData.userName}`;
            emailElement.textContent = `${userData.userEmail}`;
        })
        .catch(error => {
            console.error('Error fetching user data:', error);
        });


    if (stockForm) {
        stockForm.addEventListener("submit", function (event) {
            event.preventDefault();

            const stockName = document.getElementById("stockName").value;


            const stockPrice = document.getElementById("stockPrice").value;


            const stockQuantity = document.getElementById("stockQuantity").value;


            const stockData = {
                stockName: stockName,
                stockPrice: parseFloat(stockPrice),
                stockQuantity: parseInt(stockQuantity)
            };

            // Assuming you have an API endpoint to handle form submissions
            fetch("http://localhost:8080/stocks/add", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(stockData)
            })
                .then(response => response.json())
                .then(data => {
                    showNotification("Your Stock Was Added Successfully !!"); // Display the notification
                    stockForm.reset();   // Clear the form
                })
                .catch(error => {
                    console.error("Error:", error);
                    alert("An error occurred while adding the stock.");
                });

            interaction = 'Stock Details Submitted';
            sendUserInteractionToServer(interaction);

        });
    }
    if (reduceStockForm) {

        stockIdInput.addEventListener("click", function (event) {
            event.preventDefault(); // Prevent default behavior of input area
            stockNameOptions.classList.toggle("dropdown-content");

            if (stockNameOptions.classList.contains("dropdown-content")) {
                fetch("http://localhost:8080/stocks/showData")
                    .then(response => response.json())
                    .then(stockData => {
                        console.log("inside data area");
                        stockNameOptions.innerHTML = ""; // Clear existing options

                        stockData.forEach(data => {
                            const option = document.createElement("div");
                            option.className = "dropdown-option";
                            option.textContent = data.stockName;
                            option.addEventListener("click", function () {
                                stockIdInput.value = data.stockId; // Set the stock ID directly from the data object

                                stockNameOptions.classList.remove("dropdown-content"); // Hide dropdown
                            });
                            stockNameOptions.appendChild(option);
                        });
                    });
            } else {
                stockNameOptions.innerHTML = ""; // Clear options when closing dropdown
            }
        });


        reduceStockForm.addEventListener("submit", function (event) {
            event.preventDefault();

            const stockId = document.getElementById("stockIdInput").value;
            const reduceAmount = parseInt(document.getElementById("stockQuantity").value);

            fetch(`http://localhost:8080/stocks/reduce?id=${stockId}&quantity=${reduceAmount}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                }
            })
                .then(response => response.json())
                .then(data => {
                    showNotification("Your Stock Was Reduced Successfully !!");
                    tableBody.innerHTML = "";

                    // Iterate through the JSON object and populate the table
                    for (const key in data) {
                        if (data.hasOwnProperty(key)) {
                            const row = document.createElement("tr");
                            const keyCell = document.createElement("td");
                            const valueCell = document.createElement("td");

                            keyCell.textContent = key;
                            valueCell.textContent = data[key];

                            row.appendChild(keyCell);
                            row.appendChild(valueCell);

                            tableBody.appendChild(row);
                        }
                    }
                })
        });
    }

    if (showStockForm) {
        stockIdInput.addEventListener("click", function (event) {
            event.preventDefault(); // Prevent default behavior of input area
            stockNameOptions.classList.toggle("dropdown-content");

            if (stockNameOptions.classList.contains("dropdown-content")) {
                fetch("http://localhost:8080/stocks/showData")
                    .then(response => response.json())
                    .then(stockData => {
                        console.log("inside data area");
                        stockNameOptions.innerHTML = ""; // Clear existing options

                        stockData.forEach(data => {
                            const option = document.createElement("div");
                            option.className = "dropdown-option";
                            option.textContent = data.stockName;
                            option.addEventListener("click", function () {
                                stockIdInput.value = data.stockId; // Set the stock ID directly from the data object
                                stockNameOptions.classList.remove("dropdown-content"); // Hide dropdown
                            });
                            stockNameOptions.appendChild(option);
                        });
                    });
            } else {
                stockNameOptions.innerHTML = ""; // Clear options when closing dropdown
            }
        });
        showStockForm.addEventListener("submit", function (event) {
            event.preventDefault();

            const stockId = document.getElementById("stockIdInput").value;
            console.log("stockID: ", stockId);

            fetch(`http://localhost:8080/stocks/show/${stockId}`, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            })
                .then(response => response.json())
                .then(data => {
                    showNotification("Your Stock Was Found Successfully !!");
                    tableBody.innerHTML = "";

                    // Iterate through the JSON object and populate the table
                    for (const key in data) {
                        if (data.hasOwnProperty(key)) {
                            const row = document.createElement("tr");
                            const keyCell = document.createElement("td");
                            const valueCell = document.createElement("td");

                            keyCell.textContent = key;
                            valueCell.textContent = data[key];

                            row.appendChild(keyCell);
                            row.appendChild(valueCell);

                            tableBody.appendChild(row);
                        }
                    }
                })
        });
    }
    if (placeOrderForm) {

        orderId.addEventListener("click", function (event) {
            event.preventDefault(); // Prevent default behavior of input area
            orderDataOptions.classList.toggle("dropdown-content");

            if (orderDataOptions.classList.contains("dropdown-content")) {
                fetch("http://localhost:8081/orders/showData")
                    .then(response => response.json())
                    .then(orderData => {
                        console.log("inside order data area");
                        orderDataOptions.innerHTML = ""; // Clear existing options

                        orderData.forEach(data => {
                            const option = document.createElement("div");
                            option.className = "dropdown-option";
                            option.innerHTML = "Stock Name: " + data.stockNameData + "<br>" +
                                "Order Quantity: " + data.orderQuantityData + "<br>" +
                                "Order Amount: " + data.orderAmountData + "<br>" +
                                "Order Status: " + data.orderStatus;

                            option.addEventListener("click", function () {
                                orderId.value = data.orderId; // Set the stock ID directly from the data object
                                orderDataOptions.classList.toggle("dropdown-content"); // Hide dropdown
                            });
                            orderDataOptions.appendChild(option);
                        });
                    });
            } else {
                orderDataOptions.innerHTML = ""; // Clear options when closing dropdown
            }
        });


        placeOrderForm.addEventListener("submit", function (event) {
            event.preventDefault();

            const orderId = document.getElementById("orderId").value;
            const paymentMode = document.querySelector('input[name="paymentMode"]:checked').value;

            const orderData = {
                orderId: parseInt(orderId),
                paymentMode: paymentMode
            };

            // Assuming you have an API endpoint to handle form submissions
            fetch("http://localhost:8081/orders/place", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(orderData)
            })
                .then(response => response.json())
                .then(data => {
                    showNotification("Your Order Was Placed Successfully !!"); // Display the notification
                    placeOrderForm.reset();   // Clear the form

                    for (const key in data) {
                        if (data.hasOwnProperty(key)) {
                            const row = document.createElement("tr");
                            const keyCell = document.createElement("td");
                            const valueCell = document.createElement("td");

                            keyCell.textContent = key;
                            valueCell.textContent = data[key];

                            row.appendChild(keyCell);
                            row.appendChild(valueCell);

                            tableBody.appendChild(row);
                        }
                    }
                })

                .catch(error => {
                    console.error("Error:", error);
                    alert("An Error Occurred While Placing The Order.");
                });
        });
    }


    if (addCartForm) {
        stockIdInput.addEventListener("click", function (event) {
            event.preventDefault(); // Prevent default behavior of input area
            stockNameOptions.classList.toggle("dropdown-content");

            if (stockNameOptions.classList.contains("dropdown-content")) {
                fetch("http://localhost:8080/stocks/showData")
                    .then(response => response.json())
                    .then(stockData => {
                        console.log("inside data area");
                        stockNameOptions.innerHTML = ""; // Clear existing options

                        stockData.forEach(data => {
                            const option = document.createElement("div");
                            option.className = "dropdown-option";
                            option.textContent = data.stockName;
                            option.addEventListener("click", function () {
                                stockIdInput.value = data.stockId; // Set the stock ID directly from the data object
                                stockNameOptions.classList.remove("dropdown-content"); // Hide dropdown
                            });
                            stockNameOptions.appendChild(option);
                        });
                    });
            } else {
                stockNameOptions.innerHTML = ""; // Clear options when closing dropdown
            }
        });


        addCartForm.addEventListener("submit", function (event) {
            event.preventDefault();
            console.log("Inside listener cart if condition");


            const stockId = document.getElementById("stockIdInput").value;
            const orderQuantity = document.getElementById("orderQuantity").value;

            const cartData = {
                stockId: parseInt(stockId),
                orderQuantity: parseInt(orderQuantity),
            };

            // Assuming you have an API endpoint to handle form submissions
            fetch("http://localhost:8081/orders/add", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(cartData)
            })
                .then(response => response.json())
                .then(data => {
                    showNotification("Your Item Was Added To Cart Successfully !!"); // Display the notification
                    addCartForm.reset();   // Clear the form
                })
                .catch(error => {
                    console.error("Error:", error);
                    alert("An Error Occurred While Adding To Cart.");
                });
        });
    }


    if (showOrderForm) {

        orderId.addEventListener("click", function (event) {
            event.preventDefault(); // Prevent default behavior of input area
            orderDataOptions.classList.toggle("dropdown-content");

            if (orderDataOptions.classList.contains("dropdown-content")) {
                fetch("http://localhost:8081/orders/showData")
                    .then(response => response.json())
                    .then(orderData => {
                        console.log("inside order data area");
                        orderDataOptions.innerHTML = ""; // Clear existing options

                        orderData.forEach(data => {
                            const option = document.createElement("div");
                            option.className = "dropdown-option";
                            option.innerHTML = "Order Id: " + data.orderId + "<br>" +
                                "Stock Name: " + data.stockNameData;


                            option.addEventListener("click", function () {
                                orderId.value = data.orderId; // Set the stock ID directly from the data object
                                orderDataOptions.classList.toggle("dropdown-content"); // Hide dropdown
                            });
                            orderDataOptions.appendChild(option);
                        });
                    });
            } else {
                orderDataOptions.innerHTML = ""; // Clear options when closing dropdown
            }
        });

        showOrderForm.addEventListener("submit", function (event) {
            event.preventDefault();

            const orderId = document.getElementById("orderId").value;
            console.log("orderID: ", orderId);

            fetch(`http://localhost:8081/orders/show/${orderId}`, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            })
                .then(response => response.json())
                .then(data => {
                    showNotification("Your Order Was Found Successfully !!");
                    tableBody.innerHTML = "";

                    // Iterate through the JSON object and populate the table

                    for (const key in data) {
                        if (data.hasOwnProperty(key)) {
                            const row = document.createElement("tr");
                            const keyCell = document.createElement("td");

                            keyCell.textContent = key;
                            row.appendChild(keyCell);

                            const value = data[key];
                            const valueCell = createTableCell(value);

                            row.appendChild(valueCell);

                            tableBody.appendChild(row);
                        }
                    }

                })
        });
    }

    function showNotification(message) {
        console.log("notif");
        const notification = document.getElementById('notification');
        notification.textContent = message;
        notification.style.display = 'block';
        setTimeout(() => {
            notification.style.display = 'none';
        }, 4000); // Display for 3 seconds
    }
    function createTableCell(value) {
        if (typeof value === "object" && value !== null) {
            const subTable = document.createElement("table");
            const subTableBody = document.createElement("tbody");

            for (const subKey in value) {
                if (value.hasOwnProperty(subKey)) {
                    const subRow = document.createElement("tr");
                    const subKeyCell = document.createElement("td");
                    const subValueCell = createTableCell(value[subKey]); // Recursive call for nested objects

                    subKeyCell.textContent = subKey;

                    subRow.appendChild(subKeyCell);
                    subRow.appendChild(subValueCell);

                    subTableBody.appendChild(subRow);
                }
            }

            subTable.appendChild(subTableBody);

            const cell = document.createElement("td");
            cell.appendChild(subTable);
            return cell;
        } else {
            const cell = document.createElement("td");
            cell.textContent = value;
            return cell;
        }
    }


});
