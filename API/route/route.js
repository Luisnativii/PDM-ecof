const express = require('express');

const model = require('../model/family');

const router = express.Router();

//Metodos

//Insertar 
router.post('/postfamily', async (req, res) => {

    const data = new model(
        {
            familyName: req.body.familyName,
            location: req.body.location,
            geographicLocation: req.body.geographicLocation,
            houseType: req.body.houseType,
            risk: req.body.risk,
            familymembers: req.body.familymembers
        });


        try {
            const dataToSave = await data.save();
            res.status(200).json({"Result": "ok"});
        }
        catch (error) {
            res.status(400).json({"message": error.message});
        }

});



//Selecionar Todos POST:

router.get('/getfamily', async (req, res) => {

    try {
        
        //el find es como un select * from table

        const data = await model.find();
        res.status(200).json(data);
    }
    catch (error) {
        res.status(400).json({"Result": "Error", "Error": error});
    }
});



//Actualizar por ID PATCH

/*router.patch('/updatecurse', async (req, res) => {

    try {
        
        //el find es como un select * from table
        const id = req.query.id;
        const updatedata = req.body;
        const option = {new: true};

        const Result = await model.findByIdAndUpdate(
            id,
            updatedata,
            option
        );
        res.status(200).json(Result);
    }
    catch (error) {
        res.status(400).json({"Result": "Error", "Error": error});
    }
});

//Eliminar por ID

router.delete('/updatecurse', async (req, res) => {

    try {
        
        //el find es como un select * from table
        const id = req.query.id;
        

        const Result = await model.findByIdAndUpdate(
            id,
            updatedata,
            option
        );
        res.status(200).json(Result);
    }
    catch (error) {
        res.status(400).json({"Result": "Error", "Error": error});
    }
});
*/

module.exports = router;