// Arrays in js
// https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array

// arrays can be delared explicitly
const array = [8, 9, 3, 4, 'asd', {}]; // notice types don't matter
// array = 4; //can't do this
array[4] = 6; // can do this

array.b = 'test';
console.log(array)

// or with new keyword
const anotherArray = new Array(5);

// access array elements
console.log(array[0]);

// change elements
array[1] = 'hello';

const arrowFunction = (item) => console.log(item); // same as java

array.forEach(item => console.log(item)); // foreach works without having to call stream

const newArray = array.map(item => item + 1) // notice js arrow functions have "=" for arrow
  .filter(i => i % 2 === 0); // no need for collect
console.log(newArray)

