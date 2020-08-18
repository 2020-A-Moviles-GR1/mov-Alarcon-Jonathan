/**
 * Usuario.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  // Aqui se puede definir el nombre de la tabla 
  tableName:'epn_usuario',

  attributes: {

    
    cedula: { // nombre de atributo
      type: 'string', // Tipo del atributo
      required: true, // Es requerido
      columnName: 'epn_cedula', // Nombre de la columna
      unique: true, // Debe ser único
      minLength: 10,
      maxLength: 25
    },

    nombre: {
      type: 'string',
      minLength: 3,
      required: true // Por defecto es false
    },

    correo: {
      type: 'string',
      isEmail: true
    },

    estadoCivil: {
      type: 'string',
      isIn: ['Soltero','Casado','Divorciado','Viudo','Union Libre'],
      defaultsTo: 'Soltero'
    },

    password: {
      type: 'string',
      regex: /^[a-zA-Z]\w{3,14}$/
    },

    pokemons: { // Relacion de 1 a muchos por eso va en plural
      collection: 'pokemon', // hacemos referencia al modelo
      via: 'usuario' // Nombre de la foreign key en 'Pokemnon' // con que Atributo se va a relacionar
    }
    
  },

};

