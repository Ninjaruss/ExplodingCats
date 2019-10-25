const promiseExample = () => {
  return new Promise((resolve, reject) => {
    // Do anything you want
    // finishes when resolve() is called      
    setTimeout(() => {
      resolve();
     }, 3000);
  });
};

promiseExample() // This happens async
  .then((res) => console.log(res)) // Don't know when this line will trigger
  .catch((e) => console.log('Something went wrong!'));

console.log('hi');