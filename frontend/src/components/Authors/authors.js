import React from "react";

const authors = (props) => {
    return (
        <div className="container mm-4 mt-5">
            <h1>Authors List</h1>
            <table border="1" className="table table-striped table-responsive-md">
                <thead>
                <tr>
                    <th scope={"col"}>Name</th>
                    <th scope={"col"}>Surname</th>
                    <th scope={"col"}>Country</th>
                </tr>
                </thead>
                <tbody>
                {props.authors.map((term, index) => {
                    return (
                        <tr>
                            <td>{term.name}</td>
                            <td>{term.surname}</td>
                            <td>{term.country.name}</td>
                        </tr>
                    );
                })}
                </tbody>
            </table>
        </div>

    );
}

export default authors;