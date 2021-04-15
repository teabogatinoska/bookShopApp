import React from "react";

const countries = (props) => {
    return (
        <div class="container mm-4 mt-5">
            <h1>Countries List</h1>
            <table border="1" class="table table-striped table-responsive-md">
                <thead>
                <tr>
                    <th scope={"col"}>Country Name</th>
                    <th scope={"col"}>Continent</th>
                </tr>
                </thead>
                <tbody>
                {props.countries.map((term) => {
                    return (
                        <tr>
                            <td>{term.name}</td>
                            <td>{term.continent}</td>
                        </tr>
                    );
                })}
                </tbody>
            </table>
        </div>

    );
}

export default countries;