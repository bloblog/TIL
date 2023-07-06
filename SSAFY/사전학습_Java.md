## [생활코딩 > Java](https://www.opentutorials.org/course/1223/4551)

<br>

## 설치 및 실행

---

### 관련 용어

- JDK(Java Development Kit) : Java 프로그램을 실행하면 Java 코드를 컴파일하는 컴파일러와 개발에 필요한 각종 도구 그리고 JRE가 포함되어 있다. 즉 개발자를 위한 자바 버전
- JRE(Java Runtime Environment) : 자바가 실제로 동작하는 데 필요한 JVM, 라이브러리, 각종 파일들이 포함되어 있다. 자바로 만들어진 프로그램을 구동하려고 할 때 필요
- JVM(Java Virtual Machine) : 자바가 실제로 구동하는 환경. 자바로 만들어진 소프트웨어는 JVM이라는 가상화된 환경에서 구동된다. 즉 하나의 자바 프로그램을 만들면 어떤 환경에서도 실행할 수 있게 한다.

### 코드

- 확장자 : `.java`
- 예제 파일
    
    [Helloworld.java](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/230e3f1d-08e1-4982-89f1-afced43f8a20/Helloworld.java)
    

### 컴파일 및 실행

- 컴파일러 `javac`
    
    ```java
    javac Helloworld.java
    ```
    
    -> 컴파일하면 실행파일 생성됨
    
- 컴파일된 파일을 실행시켜주는 프로그램 런처(launcher) 이용
    
    ```java
    java Helloworld
    ```
    

- 구체적인 작동 방식
    
    ![java1](https://github.com/bloblog/TIL/blob/main/image/java1.png?raw=true)
    
</br>

## 개발도구와 이클립스

---

### 이클립스 개요

- 가장 대표적인 자바의 개발 도구
- __IDE(통합 개발 환경)__ 로 분류됨
    - 소스 편집기는 기본이고, 컴파일러, 디버거, 유닛테스트와 같은 도구들이 결합되어 있는 거대 소프트웨어를 포괄적으로 의미

### 이클립스 사용법

- 예제 생성 (w/ 이클립스)
    - Create Java > `Java_tutorial 폴더` 만들어서 해당 폴더를 경로로 하는 프로젝트 생성 > 폴더 내부에 bin, src 폴더 자동으로 생성됨
        
        
        ![java2](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ea78f694-de9d-4bd7-beaf-2a4b767bc34c/Untitled.png)
        
        - bin (binary) : 실행파일. 즉 `.class` 파일
        - src (source) : `.java` 파일
        
    - `New > Package` : 패키지 먼저 생성. 디렉토리 같은 역할
        - 일반적으로 도메인 주소(유일, 중복 방지)를 패키지 이름으로 사용
        - 패키지 이름의 `.` : 폴더 안의 폴더를 만들어준다.
    - `생성된 패키지 우클릭 > New > Class` : 클래스 생성하기
        - 자동으로 자바파일 만들어줌. 편리하게 생성 가능
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/d5d0661a-551f-4634-80e0-4738687dd38f/Untitled.png)
        
        ```java
        package org.opentutorials.javatutorials.eclipse;
        
        public class Helloworld {
        
        	public static void main(String[] args) {
        		// TODO Auto-generated method stub
        		// 이 부분에 실행되길 원하는 소스코드 작성하면 된다.
        
        	}
        
        }
        ```
        
- +) Ideone
    
    [Ideone.com](https://ideone.com/)
    
    - 온라인에서 소스코드를 공유하고 직접 실행해 볼 수 있는 도구
    - 개발환경이 갖춰지지 않아도 코드 테스트 가능

## 숫자와 문자

---

### 숫자

- 따옴표가 없는 숫자 = 숫자로 인식
- 예제
    
    ```java
    System.out.println(1+2); 
    // 결과 : 3
    
    System.out.println(1.2+1.3);
    // 결과 : 2.5
    
    System.out.println(2*5);
    // 결과 : 10
    
    System.out.println(6/2);
    // 결과 : 3
    ```
    

### 문자와 문자열

- 문자 (Character) : 한 글자
    - 작은 따옴표(큰 따옴표도 가능)로 감싸야 함
- 문자열 (String) : 여러 개의 문자
    - 큰 따옴표로 감싸야 함
- 예제
    
    ```java
    System.out.println('생');
    
    System.out.println("생활코딩");
    ```
    

- 이스케이프
    - 문자열 안에 큰 따옴표 넣기 : `\` 사용
    
    ```java
    System.out.println("egoing said \"Welcome programming world\"");
    ```
    
- 여러 줄 표시하기
    
    ```java
    System.out.println("HTML**\n**CSS**\n**JavaScript**\n**");
    ```
    
- 문자의 연산 : `+` 사용 가능
    
    ```java
    System.out.println("생활"+"코딩");
    ```
    

## 변수

---

### 선언과 할당

- 데이터 형식 명시 → 다른 데이터 타입 들어오면 컴파일조차 되지 않는다.
    - 정수
    
    ```java
    int a; // 데이터형식 변수이름 명시
    a = 1; // 
    System.out.println(a+1); //2
     
    a = 2;
    System.out.println(a+1); //3
    ```
    
    - 실수
    
    ```java
    double a = 1.1; // 데이터형식(실수) 변수명 = 값 형식도 가능
    System.out.println(a+1.1); // 2.2
     
    a = 2.1; 
    System.out.println(a+1.1); // 3.2
    ```
    
- +) 에러가 나는 경우
    
    ```java
    int a = 1.1; 
    System.out.println(a+1.1);
    ```
    

### 문자열 변수

```java
String first = "coding"; // first 라는 이름의 변수 선언
System.out.println(first+" "+"everybody");
```

- 변수 여러 개 동시에 선언도 가능
    
    ```java
    String a, b;
    a = "coding";
    b = " everybody";
    System.out.println(a+b);
    ```
    

### 변수의 효용

- 코드의 재활용성을 높여준다
- 변수 사용 x

```java
System.out.println(100 + 10);
System.out.println((100 + 10) / 10);
System.out.println(((100 + 10) / 10) - 10);
System.out.println((((100 + 10) / 10) - 10) * 10);
```

- 변수 사용

```java
int a = 100;
System.out.println(a + 10);
System.out.println((a+ 10) / 10);
System.out.println(((a + 10) / 10) - 10);
System.out.println((((a + 10) / 10) - 10) * 10);
```

## 주석과 세미콜론

---

### 주석

- 프로그래밍적으로 해석되지 않는다.
- 한줄 주석
    
    ```java
    public static void main(String[] args) {
        **//** 두개의 변수가 같은 데이터 타입 일 때 아래와 같이 코드를 작성한다.
        String a, b;
    }
    ```
    
- 여러줄 주석
    
    ```java
    public static void main(String[] args) {
        String a, b;
        **/***
        a = "coding";
        b = "everybody";
        System.out.println(a+b);
        ***/**
    ```
    
- JavaDoc 주석
    - 자바의 api 문서 만들 때 사용
    - 밑의 코드에 대한 문서 만들어준다.
    
    ```java
    **/****
     * Prints an integer and then terminate the line.  This method behaves as
     * though it invokes <code>{@link #print(int)}</code> and then
     * <code>{@link #println()}</code>.
     *
     * @param x  The <code>int</code> to be printed.
     ***/**
    public void println(int x) {
        synchronized (this) {
            print(x);
            newLine();
        }
    }
    ```
    

### 세미콜론

- 문장 끝에 반드시 사용. 아니면 컴파일 에러남

```java
// assignment statement
aValue = 8933.234;
// increment statement
aValue++;
// method invocation statement
System.out.println("Hello World!");
// object creation statement
Bicycle myBike = new Bicycle();
```

- 여러 문장 한 줄로 작성할 때도 사용

```java
int a = 100; double b = 10.1;
```

## 데이터 타입

---

### 데이터 타입 개요

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ffd0a388-0712-41c3-8b62-be778d8f0a36/Untitled.png)

- 비트 = 0과 1

### 데이터 타입 비교

- 정수형
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/9c0397c3-3e19-4019-a84f-696affc3d954/Untitled.png)
    
    - 길이에 상관 없이 특정 데이터 타입을 사용하면 차지하는 메모리 동일
        
        ```java
        // 둘 다 8바이트 사용
        long a = 2147483647;
        long b = 1;
        ```
        
    - 주로 `int`를 사용 → 메모리 용량이 늘어났고(`byte` 를 쓸 정도까진 아님) 충분히 큰 수를 표현할 수 있기 때문에
        
        
- 실수형
    - float : 4바이트
    - double : 8바이트 / 실수형의 기본 포맷
- 문자
    - `char` : 한 글자당 2바이트
    - e.g. 6글자 가진 String 타입 변수 = 12바이트 차지

### 상수의 데이터 타입

- 상수 : 변하지 않는 값 (↔ 변수)
    - 고유한 값을 갖고 있고, 그 값을 변경할 수 없다.
- 실수의 표현
    - 기본은 double
    
    ```java
    // 정상적으로 컴파일됨
    double a = 2.2;
    ```
    
    ```java
    // 냅다 float 쓰면 에러난다
    float a = 2.2;
    
    // 하지만 데이터 타입 명시하면 괜찮다!
    float a = 2.2F;
    ```
    
- 정수의 표현
    - 에러 1 - 최댓값 넘음
    - 에러 2 - 변수와 상수의 타입이 일치하지 않음
        
        ```java
        int a = 2147483648;
        
        long a = 2147483648;
        ```
        
    - 해결 → `디폴트 x 데이터타입` 변수 = 상수`동일한 데이터타입`;
        
        ```java
        long a = 2147483648L;
        ```
        
    - +) byte, short는 int 형 허용
        
        ```java
        // 에러 x
        byte a = 100;
        short b = 200;
        ```
        

### 형변환

<aside>
❓ **형변환 하는 이유**

같은 값이라도 데이터 타입이 다르면 비트값이 완전히 다르므로, 서로 다른 형식끼리 계산하려면 형변환이 필요하다!

</aside>

- 자동 형 변환
- 명시적 형 변환

## 연산자

---

### 비교와 Boolean

### 조건문

### 논리 연산자

### 반복문

## 배열

---