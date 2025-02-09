// firebase.js
import { initializeApp } from "firebase/app";
import { getDatabase } from "firebase/database";

const firebaseConfig = {
    // ... other Firebase config values
    databaseURL: 'https://splitshare-4a69d-default-rtdb.firebaseio.com' 
  };

// Initialize Firebase
const app = initializeApp(firebaseConfig);

// Initialize Realtime Database
export const database = getDatabase(app);
