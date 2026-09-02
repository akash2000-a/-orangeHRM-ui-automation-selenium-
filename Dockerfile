# Step 1: Base image with Java 17 and maven pre-installed
FROM maven:3.9.6-eclipse-temurin-17-alpine


# Step 2: Install Google Chrome and dependencies inside the container
RUN apk add --no-cache \
    chromium \
    chromium-chromedriver \
    ttf-freefont \
    font-noto-emoji

# Step 3: Tell Selenium where chromium binary is located on alpine linux
ENV CHROME_BIN=/usr/bin/chromium-browser
ENV CHROME_DRIVER_BIN=/usr/bin/chromedriver

# Step 4: Set the working directory inside the container
WORKDIR /app

#Step 5: Copy the pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Step 6: Copy source code and resource to container
COPY src ./src
COPY testng.xml .

# Step 7: Command to run when container starts
CMD ["mvn", "clean", "test"]