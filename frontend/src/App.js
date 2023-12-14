import "./App.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import NavigationComponent from "./components/NavigationComponent";

function App() {
  return (
    <Router basename={process.env.PUBLIC_URL}>
      <div>
        <NavigationComponent />
      </div>
    </Router>
  );
}

export default App;
