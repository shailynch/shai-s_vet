'use strict';
const customerURL = "http://localhost:8088/customer";
const _first_name = document.querySelector("#first_name");
const _last_name = document.querySelector("#last_name");
const _email = document.querySelector("#email");

const petURL = "http://localhost:8088/pet";
const _pet_name = document.querySelector("#pet_name");
const _pet_type = document.querySelector("#pet_type");
const _customer_id = document.querySelector("#customer_id");

const vetURL = "http://localhost:8088/vet";
const _vet_first_name = document.querySelector("#vet_first_name");
const _vet_last_name = document.querySelector("#vet_last_name");
const _vet_email = document.querySelector("#vet_email");

const appointmentURL = "http://localhost:8088/appointment";
const _appointment_customer_id = document.querySelector("#appointment_customer_id");
const _appointment_vet_id = document.querySelector("#appointment_vet_id");
const _appointment_pet_id = document.querySelector("#appointment_pet_id");


const addCustomer = () => {
    const customerFirstName  = _first_name.value;
    const customerLastName  = _last_name.value;
    const customerEmail = _email.value;
    
    let data = { 
        "firstName": customerFirstName, 
        "lastName": customerLastName, 
        "email": customerEmail 
    } 
    

    fetch(`${customerURL}/add`, {
        method: "POST",
        body: JSON.stringify(data),
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(response => response.json())
        .then(model => {
            console.log(model);
            allFromCustomer();
        })
        .catch(err => console.error(`error ${err}`));
};

const addVet = () => {
    const vetFirstName  = _vet_first_name.value;
    const vetLastName  = _vet_last_name.value;
    const vetEmail = _vet_email.value;
    
    let data = { 
        "firstName": vetFirstName, 
        "lastName": vetLastName, 
        "email": vetEmail 
    } 


    fetch(`${vetURL}/add`, {
        method: "POST",
        body: JSON.stringify(data),
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(response => response.json())
        .then(model => {
            console.log(model);
            allFromCustomer();
        })
        .catch(err => console.error(`error ${err}`));
        
        
};

const addPet = () => {
    const petName  = _pet_name.value;
    const petType  = _pet_type.value;
    const customerID = _customer_id.value;
    
    let data = { 
        "name": petName, 
        "type": petType, 
        "customerID": customerID 
    } 

    fetch(`${petURL}/add`, {
        method: "POST",
        body: JSON.stringify(data),
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(response => response.json())
        .then(model => {
            console.log(model);
            allFromCustomer();
        })
        .catch(err => console.error(`error ${err}`));

};

const addAppointment = () => {
	
    const petID = _appointment_pet_id.value;
    const customerID = _appointment_customer_id.value;
    const vetID = _appointment_vet_id.value;
    
    let data = { 
        "vetID": vetID, 
        "petID": petID, 
        "customerID": customerID 
    } 

    fetch(`${appointmentURL}/add`, {
        method: "POST",
        body: JSON.stringify(data),
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(response => response.json())
        .then(model => {
            console.log(model);
            allFromCustomer();
        })
        .catch(err => console.error(`error ${err}`));

};

const deleteTask = (taskId) => {
    fetch(`${taskUrl}/delete/${taskId}`, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(response => console.log(response))
        .then(() => readAllTasks())
        .catch(err => console.error(`error ${err}`));
};