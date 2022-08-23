# -*- coding: utf-8 -*-
"""
Spyder Editor

This is a temporary script file.
"""


import numpy as np
import pickle
import pandas as pd
#from flasgger import Swagger
import streamlit as st 

from PIL import Image

#app=Flask(__name__)
#Swagger(app)

pickle_in = open("neigh.pkl","rb")
neigh=pickle.load(pickle_in)

#@app.route('/')
def welcome():
    return "Welcome All"

#@app.route('/predict',methods=["Get"])
def predict_note_authentication(greq,grev,grea,cgpa):
    
    """Let's predict university 
    This is using docstrings for specifications.
    ---
    parameters:  
      - name: greq
        in: query
        type: number
        required: true
      - name: grev
        in: query
        type: number
        required: true
      - name: grea
        in: query
        type: number
        required: true
      - name: cgpa
        in: query
        type: number
        required: true
    responses:
        200:
            description: The output values
        
    """
   
    prediction=neigh.predict([[greq,grev,grea,cgpa]])
    print(prediction)
    return prediction


import base64
def add_bg_from_local(image_file):
    with open(image_file, "rb") as image_file:
        encoded_string = base64.b64encode(image_file.read())
    st.markdown(
    f"""
    <style>
    .stApp {{
        background-image: url(data:image/{"jpeg"};base64,{encoded_string.decode()});
        background-size: cover
    }}
    </style>
    """,
    unsafe_allow_html=True
    )
add_bg_from_local('C:/Users/acer/Downloads/university_image.jpeg')
def main():
    st.title("University recommendation System ")
    html_temp = """
    <div style="background-color:black;padding:10px">
    
    </div>
    """
    
    st.markdown(html_temp,unsafe_allow_html=True)
    greq = st.text_input("Enter Your GRE Quant Score"," ")
    grev = st.text_input("Enter Your GRE Verbal Score"," ")
    grea = st.text_input("Enter Your GRE Analytical Score"," ")
    cgpa = st.text_input("Enter Your CGPA Score"," ")
    result=""
    if st.button("Predict"):
        result=predict_note_authentication(greq,grev,grea,cgpa)
    st.success('{}'.format(result))
    if st.button("About"):
        st.text("Made By")
        st.text("Ujjwal Parth Pranav Simran")

if __name__=='__main__':
    main()
    