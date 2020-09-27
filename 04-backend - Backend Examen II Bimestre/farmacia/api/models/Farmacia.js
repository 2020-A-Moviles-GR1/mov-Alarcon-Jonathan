/**
 * Farmacia.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  tableName: 'farmacia',

  attributes: {

    nombreFarmacia: {
      type: 'string',
      required: true,
      minLength: 5,
      maxLength: 25
    },

    direccionFarmacia: {
      type: 'string',
      required: true,
      minLength: 5,
      maxLength: 25
    },

    numeroTrabajadores: {
      type: 'number'
      
    },

    compra: {
      type: 'number'
    },

    atencion: {
      type: 'boolean'
    },

    medicamentos: {
      collection: 'medicamento',
      via: 'farmacia'
    }

  },

};

