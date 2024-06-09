require ('dotenv').config();

const express = require('express');
const mongoose = require('mongoose');
const mongoString = process.env.DATABASE_URL;
const router = require('./route/route');

mongoose.connect(mongoString);
const database = mongoose.connection;

database.on('error', (error) => console.log('Error al conectar a la base de datos', error));

database.once('connected', () => console.log('Conectado a la base de datos'));

const app = express();

app.use(express.json());
app.use('/api', router);

app.listen(3000, () => {console.log('Servidor corriendo en el puerto 3000')});

