import React, { useState } from "react";
import axios from "axios";
import { log } from "./logger";

function App() {
  const [xsltText, setXsltText] = useState("");
  const [result, setResult] = useState(null);
  const [error, setError] = useState(null);

  const handleUpload = async () => {
    const blob = new Blob([xsltText], { type: "application/xml" });
    const file = new File([blob], "mapping.xslt");
    const formData = new FormData();
    formData.append("xslt", file);

    try {
      log("Submitting XSLT");
      const response = await axios.post("http://localhost:28086/api/xslt/analyze", formData);
      setResult(response.data);
      setError(null);
    } catch (err) {
      setError(err.response?.data || "Error occurred");
      setResult(null);
    }
  };

  return (
    <div style={{ padding: 20, fontFamily: "Arial" }}>
      <h1>XSLT Analyzer</h1>
      <textarea
        rows="10"
        cols="80"
        placeholder="Paste XSLT here"
        value={xsltText}
        onChange={(e) => setXsltText(e.target.value)}
      />
      <br />
      <button onClick={handleUpload}>Analyze</button>

      {error && <p style={{ color: "red" }}>{error}</p>}

      {result && (
        <>
          <h2>Sample Input</h2>
          <pre>{result.sampleInput}</pre>

          <h2>Sample Output</h2>
          <pre>{result.sampleOutput}</pre>

          <h2>Mappings</h2>
          <table border="1" cellPadding="5">
            <thead>
              <tr>
                <th>Input XPath</th>
                <th>Output Element</th>
              </tr>
            </thead>
            <tbody>
              {result.mappings.map((m, idx) => (
                <tr key={idx}>
                  <td>{m.inputPath}</td>
                  <td>{m.outputPath}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </>
      )}
    </div>
  );
}

export default App;
