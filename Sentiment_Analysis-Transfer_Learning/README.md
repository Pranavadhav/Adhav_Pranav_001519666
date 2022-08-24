# Web Scraping Prefab

This prefab depicts the capabilities of web scraping in both R and Python. The website scraped in this handbook is the IMDB website https://www.imdb.com/list/ls047677021/ which is a repository of movies and user reviews and ratings for each of them. The handbooks demonstrates two use cases:

1. Scrape, clean and analyze data of top 100 featured movies of 2018 from IMDB using rvest in R and BeautifulSoup in Python
2. Scrape, clean and analyze data of user reviews for a specific movie using RSelenium and rvest in R and Selenium and BeautifulSoup in Python

The purpose of this handbook is to provide an overview of the web-scraping capabilities in Python and R and provide functions to perform common scraping tasks. Since websites differ in format and structure, these functions will need to be tweaked as per the organization of the website to be scraped.

## Architecture Diagram

![Sentiment Analysis Architecture Diagram](./assets/Sentiment_Analysis.png)

## Requirements

**NOTE: Please ensure you have Administrator access in the machine (Windows/Ubuntu/macOS)**

The minimum hardware requirements for this project are:


**RAM :** Minimum 8 GB (16 GB recommended for optimum performance)

**Disk space :** Minimum of 100 MB free space needed to run the program

The minimum software requirements for this project are:


| Software                             | How to Check the version available                               |
| :---                                 | ---:                                                             |
| Python 3.6                           | Open the terminal using cmd, type python -V and press enter      |
| R >=3.6                              | Open the terminal using cmd, type R --version and press enter    |
| docker                               | Open the terminal using cmd, type docker version and press enter |

**NOTE :** Support for the maintenance of Python 2.7 has officially stop since January, 2020. So it is imperative to use Python 3. If your system doesn't have Python 3, please get in touch with the IT team to install it.

### Installing System Level Dependencies

**Note:** The following instructions are applicable for Ubuntu 18.04 or higher

The following system level dependencies have to be configured before installing the libraries present in the R files

#### tesseract

Run the following commands in bash before installing tesseract package:

- sudo apt-get install libpoppler-cpp-dev
- sudo apt-get install libapparmor-dev

#### magick

Run the following commands in bash before installing tesseract package:

- sudo apt update
- sudo apt build-dep imagemagick
- wget https://www.imagemagick.org/download/ImageMagick.tar.gz
- tar xf ImageMagick.tar.gz
- cd ImageMagick-7*
- ./configure
- make
- sudo make install
- sudo ldconfig /usr/local/lib

#### rlist

Run the following commands in bash before installing rlist package:
- sudo apt update
- sudo apt-get install libxml2


### Note on using RSelenium

#### Ubuntu

Before connecting to Selenium to scrape data, a backend server has to be setup with chrome
as the headless driver. RSelenium in this project acts as a client which makes a request to the server which can be set up either in the local machine or remote server

1. To start off, install docker in the target server machine following the instruction given in the link below:

     https://phoenixnap.com/kb/how-to-install-docker-on-ubuntu-18-04

2. Once docker is installed, run the following command to start the docker container for selenium

     sudo docker run -d -p 4444:4444 -v /dev/shm:/dev/shm selenium/standalone-chrome:3.141.59-20200525

The docker is now exposed in port 4444, we can now proceed with the R scripts

#### Windows

* Download and install Selenium from this [link](https://docs.docker.com/toolbox/toolbox_install_windows/)
* Enable hardware virtualization. It is sufficient to enable it in BIOS, and subsequent steps are unnecessary. If you don’t have it enabled, Docker will throw an error. Follow instructions from this [link](https://docs.microsoft.com/en-gb/archive/blogs/canitpro/step-by-step-enabling-hyper-v-for-use-on-windows-10)
* Save [this](https://api.github.com/repos/boot2docker/boot2docker/releases/latest) json file as latest.json into /Users/{user}/.docker/machine/cache/. Upon starting, Docker will download boot2docker.iso
* Start **Docker Quickstart Terminal**. This can be found by searching in Windows start menu 
* In Docker Terminal run the following commands:
    * docker pull selenium/standalone-chrome
    * docker run -d -p 4445:4444 selenium/standalone-chrome


## Introduction

Web scraping a web page is a two-fold process:

1. Fetching - Navigating to and downloading the required page(s) using web crawlers
2. Extracting - Parse, search, reformat or copy the contents of the downloaded page using web scrapers

Packages/frameworks/modules used for the web scraping tasks demonstrated in this prefab are as below:

| Web Scraping Task                   | Python          | R         |
| :---                                | :----:          | ---:      |
| Connect to a web page               | urllib.request  | rvest     |
| Extract Link                        | BeautifulSoup   | rvest     |
| Extract Text                        | BeautifulSoup   | rvest     |
| Extract Image URL                   | BeautifulSoup   | rvest     |
| Extract data from list/table        | BeautifulSoup   | rvest     |
| Extract website with pagination     | BeautifulSoup   | rvest     |
| Download Images from URL            | urllib          | rvest     |
| Extract data from sites using AJAX  | BeautifulSoup   | rvest     |
| Extract parsed data to CSV          | BeautifulSoup   | rvest     |
| Search and extract search results   | Selenium        | RSelenium |

The choice of using the aforementioned packages for the prefab was primarily due to:
* the popularity of the packages
* large environment support for the packages
* the use case being a simple scraping task
* the crawl rate being limited to a under a 1000 hits (This limit was mandatory to prevent the scraper from being blocked by IMDB)

### Web Scraping in Python

Python offers several packages and frameworks to scrape webpages. A comparison of the most widely-used packages/frameworks are below:

|	  	                    |	Requests	                                                  |	LXML	            |	Scrapy	      |	Selenium	| BeautifulSoup |
|	:---	                  |	:----:	                                                    |	:----:	          |	:----:	      |	:----:	| ---: |
|	Type	                  |	Library	                                                    |	Library	          |	Framework	    |	Package	| Library |
|	Purpose	                |	Simplifies HTTP Requests	                                  |	Data Parser	      |	Web Crawler	  |	Browser automater	| Data Parser |
|	Ideal Use Case	        |	Simple non-recurring web scraping tasks                   	|	Complex web scraping tasks with high performance	                                                                                                | Complex and large-scale recurring web crawling/scraping tasks                    |  Small scale web-scraping on JavaScript heavy pages	|	Simple non-recurring web scraping tasks on messy documents |
|	Request Type	          |	Synchronous	                                                |	NA	              |	Asynchronous	|	Synchronous	| NA |
|	Memory and CPU usage	  |	NA	                                                        |	Low	              |	Low	          |	High	| Low |
|	Documentation	          |	Excellent	                                                  |	Good	            |	Excellent	    |	Good	| Excellent
|	Learning Curve	        |	Easy	                                                      |	Medium	          |	Hard	        |	Easy	| Easy  |
|	Support for JavaScript	|	NA	                                                        |	Unavailable	      |	Available	    |	Available	| Unavailable |
|	Selectors Available	    |	NA	                                                        |	CSS and XPath	    |	CSS and XPath	|	CSS and XPath	| CSS |


The Python packages used in this prefab are:

* Beautiful Soup - Python package for parsing HTML and XML documents
* urllib - Python package to open, read and parse URLs
* Selenium - Python package for browser automation

### Web Scraping in R

Below is a comparison of the R packages available for web-scraping:

|		|	Rvest	|	Rcrawler	|	scrapeR	|	Rselenium	|	httr	|
|	:---	|	:----:	|	:----:	|	:----:	|	:----:	|	:---	|
|	Type	|	Package	|	Package	|	Package	|	Package	|	Package	|
|	Purpose	|	Data Parser	|	Data Crawler/Parser	|	Data Parser	|	Browser Automater	|	Simplifies HTTP Requests	|
|	Ideal Use Case	|	Simple non-recurring web scraping tasks	|	Complex and large-scale recurring web crawling/scraping tasks	|	Simple non-recurring web scraping tasks	|	Small scale web-scraping on JavaScript heavy pages	|	Connect to and retrieve data from webpage	|
|	Request Type	|	Synchronous	|	Asynchronous	|	Synchronous	|	Synchronous	|	Synchronous	|
|	Memory and CPU usage	|	Low	|	Low	|	Low	|	High	|	NA	|
|	Documentation	|	Excellent	|	Good	|	Poor	|	Excellent	|	Excellent	|
|	Learning Curve	|	Easy	|	Medium	|	Difficult*	|	Easy	|	Easy	|
|	Support for JavaScript	|	Unavailable	|	Available	|	Unavailable	|	Available	|	NA	|
|	Selectors Available	|	CSS and XPath	|	CSS and XPath	|	XPath	|	CSS and XPath	|	NA	|

The R packages used in this prefab are:

* rvest - R package to parse HTML and XML documents
* RSelenium - R package for browser automation

## Use Case 1: Scrape, clean and analyze top 100 featured films of 2018

The top 100 feature films of 2018 according to IMDB rankings are scraped for their rating, gross earnings, votes, runtime and genre. An exploratory data analysis is carried out on the scraped and cleaned data.

### Data Preprocessing

The room of "Data Preprocessing" has handbooks (in R and Python) to extract and clean the scraped data from IMDB. Packages used are rvest (in R) and BeautifulSoup (in Python). These handbooks and the generated data are prerequisites to using the handbooks in the room of "Data Analysis".

### Data Analysis

For use case 1, exploratory data analysis is carried out on the scraped and cleaned data and a variety of plots are generated to visualize the analysis.

### Generate Report

The handbooks in the room of "Scraping Report Generation" generate HTML reports that visually depict the analysis carried out in the previous step.

## Use Case 2: Scrape, clean and analyze data of user reviews for a user-specified movie

Here, the use case considered is of scraping user reviews for a given movie. Review details such as reviewer name, rating, review title, date and description are saved to a CSV for analyzing the sentiment behind the reviews.

### Data Preprocessing

The room of "Data Preprocessing" has handbooks (in R and Python) to extract and clean the scraped data from IMDB. Packages used are rvest and RSelenium (in R) and BeautifulSoup and Selenium (in Python). This use case requires a Chrome browser and driver to be installed in the system. This use case evokes the Selenium server via the Chrome driver in headless mode (browser runs in the background without opening a Chrome window). It then searches for the movie of interest and extracts the URL of the user reviews page. Scraping the user reviews from this URL is done through rvest/BeautifulSoup.

### Data Analysis

Sentiment analysis is done on the user reviews scraped for a given movie.

### Generate Report

The handbooks in the room of "Scraping Report Generation" generate HTML reports that visually depict the analysis carried out in the previous step.

## Important Links

- [GitLab Link](https://mugitlab.mu-sigma.com/eoc_foundation/web-scraping-sentiment-analysis)