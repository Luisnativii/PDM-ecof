const mongoose = require('mongoose');

const DataSchema = new mongoose.Schema(
    
{
    familyName: {
        required: true,
        type: String,

    },
    location: {
        required: true,
        type: String,
        
    },
    geographicLocation: {
        required: true,
        type: String,
        
    },
    houseType: {
        required: true,
        type: String,
        
    },
    risk: {
        required: true,
        type: String,
        
    },
    familymembers: [
        {
            DUI: {
                required: true,
                type: String,
        
            },
            fullName: {
                required: true,
                type: String,
                
            },
            dateBirth: {
                required: true,
                type: String,
                
            },
            canReadWrite: {
                required: true,
                type: Boolean,
                
            },
            scholarity: {
                required: true,
                type: String,
                
            },
        }
    
    ]


});


module.exports = mongoose.model('course', DataSchema);