import React from "react";

const Categories = (props) => {
    return (


        <div className={"container mm-4 mt-5"} >
            <h1>Categories List</h1>
            <div className={"row"}>
                <div className={"table-responsive"}>
                    <table border="1" className="table table-striped table-responsive-md">
                        <thead>
                        <tr>
                            <th scope={"col"}>Name</th>
                        </tr>
                        </thead>
                        <tbody>
                        {props.categories.map((term, index) => {
                            return (
                                <tr>
                                    <td>{term}</td>
                                </tr>
                            );
                        })}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    )
}

export default Categories;