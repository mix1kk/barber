import { Provider } from 'react-redux';
import './App.css';
import Main from './Component/Main/Main_window';
import store from './Redux/Redux-Store';

function App() {
  return (
    <div>
      <Provider store = {store}>
        <Main/>
      </Provider>
      
    </div>
  );
}

export default App;
