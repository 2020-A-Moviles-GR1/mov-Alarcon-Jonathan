/**
 * Pokemon.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

   nombre: {
     type: 'string'
   },

   usuario: { // Esta es la foreign key // Many to One (nombre FK) - mismo nombre
      model: 'usuario',
      required: true // (Es opcional de 1 o 0 a muchos)
   },

   batallas: {
     collection: 'batalla',
     via: 'pokemon'
   }

  },

};

