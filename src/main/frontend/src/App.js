import "./App.css";
import { useEffect, useState } from "react";
import axios from "axios";

function App() {

const Api_key="xXjNSwPgT54O3z35As0NOHJiohiTUzq7";
const Base_Url = "http://api.giphy.com/v1/gifs/search";

const [searchGif,setSearchGif] = useState("");
const [gifs,setGifs] = useState([]);


function QueryList() {
  const [dataList, setDataList] = useState([]);

  useEffect(() => {
    companydata();
  }, []);

  const companydata = async () => {
    const { data } = await axios.get('http://localhost:8080/api/getLastRequests');
    setDataList(data);
  };

  return (
    <div className='flex-container'>
      <h4>Last 5 Queries from users:</h4>
      {dataList.map((query, index) => (
        <div key={index}>
          <h5>{query.request}</h5>
          <h5>{query.date}</h5>
        </div>
      ))}
    </div>
  );
}

const postValue = ()=>{
  gifResponse();
}

const gifResponse = async()=>{
  const ai = await (await axios(`http://localhost:8080/api/getString?string=${searchGif}`)).data
  console.log(ai)
  const response = await axios(`${Base_Url}?api_key=${Api_key}&q=${ai}&limit=1&offset=0&rating=g&lang=en`)
    setGifs(response.data.data);
   console.log(response.data.data);
 }

  return (
    <div className="App">
        <div class="searchField">
          <input
            type="text"
            class="inputs"
            placeholder="Input phrase"
            value={searchGif}
            onInput={(e)=>setSearchGif(e.target.value)}
          />
          <div class="searchButtonField">
            <button class="button-85" role="button" onClick={postValue}>
              Search
            </button>
          </div>
        </div>
        {
          gifs.map((gif)=>{
            return <img src={gif.images.fixed_height.url} />
          })
        }
        <QueryList/>
    </div>
  );
}
export default App;