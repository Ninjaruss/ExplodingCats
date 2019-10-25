// there are 3 basic ways to declare variables in js
// There are no strict types in js

// Var, but don't use this one anymore
var test = 3;
var test = 3; // lets you re-declare variables

// let, is like var, but block scoped, and not allowed to re-declare
let hello = 'world'; // Notice strings use single quotes
hello = 5; // changing the type of variable is okay, in this case string to number.
// let hello = 'world';// can't do this!

// const is the same as let, but can't change the value
const iAmAConstant = 9;
// iAmAConstant = 10; can't do this!

// objects
// variables can also be objects
const obj = {}; // obj litteral

// objects can dynamically add and remove properties
obj.a = 9;
obj.b = 10;
/*
{
  a:9,
  b:10,
}
*/

// declare an object with an object as a property
let myObject = {
  a: {

  },
  g: 'asd',
  test: true,
};
console.log(myObject)
// myObject = {}; // not allowed
//myObject.a = 3; // ok, refs can be changed
// myObject.a.b = {};
myObject.a.b.c = 4; // also ok (one or the other a time)
console.log(myObject)