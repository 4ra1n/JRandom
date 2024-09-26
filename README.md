## JRandom

`JRandom` 是一个简单的 `Java` 随机库，不同于大部分随机库，它使用独特的技术确保安全性

它会尝试使用 `CPU` 的 `RDRAND` 指令获取随机数，如果不支持将会使用 `SecureRandom`

核心代码

```asm
get_rand_int:
    test rdi, rdi
    je fail
    rdrand eax
    jc success
fail:
    xor eax, eax
    ret
success:
    mov [rdi], eax
    mov eax, 1
    ret
```

本项目已发布在 `Maven` 中央仓库可直接引入

```xml
<dependency>
    <groupId>io.github.4ra1n</groupId>
    <artifactId>jrandom</artifactId>
    <version>0.0.2</version>
</dependency>
```

在 `Java` 层使用会很简单

```java
JRandom random = new JRandom();
for (int i = 0; i < 10; i++) {
    System.out.println(random.randomString(10));
}
```

顺便支持了一些常见的 `API`
- getInt
- getInt(int start, int end) 
- randomString(int length)
- randomBytes(int length)
- randomEle(List<T> list)
- randomNumbers(int n)

目前支持 `Windows` 和 `Linux` 的 `64` 位