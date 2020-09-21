/**
 * Medicamento.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  tableName: 'medicamento',

  attributes: {

    nombreFarmacia: {
      type: 'string',
      minLength: 5,
      maxLength: 25
    },

    nombreMedicamento: {
      type: 'string',
      required: true,
      minLength: 5,
      maxLength: 25
    },

    precioMedicamento: {
      type: 'number'
    },

    fechaMedicamento: {
      type: 'string',
      minLength: 5,
      maxLength: 25
    },

    unidadesMedicamento: {
      type: 'number'
    },

    prevencion: {
      type: 'boolean'
    },

    farmacia: {
      model: 'farmacia',
      required: true
    }

  },

};

