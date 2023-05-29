package com.example.shop.config;

import com.example.shop.entities.entity.Product;
import com.example.shop.entities.entity.Role;
import com.example.shop.entities.entity.User;
import com.example.shop.service.product.ProductService;
import com.example.shop.service.role.RoleService;
import com.example.shop.service.user.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class Initialize {

    private final UserService userService;
    private final RoleService roleService;
    private final ProductService productService;

    @PostConstruct
    public void initialize() {
        Role adminRole = new Role();
        Role userRole = new Role();
        User admin = new User();
        Set<Role> roles = new HashSet<>();

        userRole.setName("ROLE_USER");
        adminRole.setName("ROLE_ADMIN");
        roles.add(adminRole);
        roles.add(userRole);
        roleService.createRole(adminRole);
        roleService.createRole(userRole);

        admin.setUsername("superAdmin");
        admin.setName("Администратор");
        admin.setEmail("admin@gmail.com");
        admin.setPassword("root");

        admin.setRoles(roles);
        userService.createUser(admin);

        List<Product> list = List.of(new Product(null, "5.99\" Смартфон DEXP A360 16 ГБ синий", 2599, "Смартфон DEXP A360 оборудован 4-ядерным процессором Mediatek MT6580P, обеспечивающим стабильную работу операционной системы Android 10. Экран диагональю 5.99’’ передает картинку с разрешением 1440x720 пикс и способен воспроизводить 16 млн цветов. Частота обновления 60 Гц предусматривает плавную смену кадров без задержек.\n" +
                        "Смартфон DEXP A360 поддерживает сети 2G и 3G, что позволяет оставаться на связи вдали от населенных пунктов. Наличие двух слотов для SIM-карт позволяет разграничить личную жизнь и работу. Литиево-ионный аккумулятор на 3000 мАч обеспечивает длительную автономную работу.", "https://c.dns-shop.ru/thumb/st4/fit/500/500/a4d518a0c79fa2ca3df780f6b5d3a7c0/970dea8647ed9fe060ad9153583856aeefb95d6bf11ecd9f2c79758d462c3a0c.jpg"),
                new Product(null, "Смартфон Xiaomi Redmi Note 10 Pro 128 ГБ", 23899, "Смартфон Xiaomi Redmi Note 10 Pro 128 ГБ отличается широкими функциональными возможностями для общения и повседневных мобильных развлечений. Он выполнен в корпусе с оригинальной фиолетовой расцветкой со степенью защищенности IP53 и оснащен дисплеем 6.67 дюйма AMOLED. Высокая производительность обеспечивается благодаря процессору Snapdragon 732G и 6 ГБ ОЗУ.\n" +
                        "Основная камера состоит из датчиков 108 Мп, широкоугольного объектива 8 Мп, макросенсора 5 Мп и сенсора глубины 2 Мп, позволяя запечатлевать реалистичные снимки в различных режимах и условиях. Камера 16 Мп с датчиком Sony позволяет создавать селфи или общаться по видеосвязи. В Xiaomi Redmi Note 10 Pro реализованы востребованные беспроводные интерфейсы Wi-Fi, Bluetooth, NFC. Аккумулятор 5020 мА*ч гарантирует длительную автономность и поддерживает стандарт ускоренной зарядки Turbo Charge.", "https://c.dns-shop.ru/thumb/st1/fit/500/500/07b85366fddc54edd3f1ece930fc6a20/5334cf6f26cdd90d189752152eca9a1299bdafca4f657e3f0dd5120b1fda8b6c.jpg"),
                new Product(null, "Смартфон Xiaomi 13 256 ГБ", 79999, "Xiaomi 13 может похвастаться AMOLED-экраном со встроенным сканером отпечатков пальцев и вырезом по центру. Устройство имеет частоту обновления 120 Гц, пиковую яркость 1900 нит, поддержку HDR10+, Dolby Vision и HLG. Смартфон оснащен 6,36-дюймовым плоским экраном с разрешением FHD+. Защита — стекло Gorilla Glass 5. Аппарат получил квадратный модуль камеры на задней панели и защиту корпуса по стандарту IP68, а так же стереодинамики с Dolby Atmos и модуль NFC.\n" +
                        "Камеры: смартфон имеет 50-мегапиксельный датчик Sony IMX800 с поддержкой OIS, 12-мегапиксельный сверхширокоугольный объектив и 10-мегапиксельный телеобъектив. Спереди у смартфона 32-мегапиксельный датчик для селфи. Камеры с настройкой от Leica.\n" +
                        "Внутри Xiaomi 13 установлен чип Snapdragon 8 Gen 2 в связке с ОЗУ LPDDR5X и ПЗУ UFS 4.0. Батарея — 4500 мА*ч с зарядкой 67 Вт.", "https://c.dns-shop.ru/thumb/st1/fit/500/500/d89e72124c10b76863b163335a5740f0/0e41c1ed91f3fcfdd169d0d0f7c1720aa35e58db8209b772f688a7ece09b64a8.jpg"),
                new Product(null, "Смартфон Xiaomi 12 128 ГБ", 44999, "Смартфон Xiaomi 12 выводит работу мобильного аппарата на высочайший уровень за счет 8-ядерного процессора Snapdragon 8 Gen 1 с 4-нанометровым техпроцессом, который лег в основу аппаратной части модели. Независимо от того, чем вы занимаетесь: снимаете фото, играете в мобильные игры или смотрите видео, вам гарантирована плавная работа устройства без единого намека на подвисания. Благодаря системе жидкостного охлаждения с увеличенной испарительной камерой устройству не страшен перегрев даже при самых интенсивных нагрузках.\n" +
                        "Смартфон Xiaomi 12 стал обладателем большого 6.28-дюймового дисплея с качественной матрицей AMOLED, обеспечивающей идеальную цветопередачу и потрясающую четкость. Даже самый динамичный контент будет отображаться на экране плавно за счет частоты обновления 120 Гц. Пиковая яркость модели достигает 1100 нит, благодаря чему вы будете видеть картинку на дисплее даже при использовании смартфона на улице в солнечную погоду. Отображение более 68 млрд цветов позволяет устройству справляться с трансляцией насыщенных изображений.", "https://c.dns-shop.ru/thumb/st1/fit/500/500/6736c0611f9876e7262f4c8c08b85857/4156d43d6ad631747f9015296ccddb9700a439d2e2ebf9414cc9432a28f11dfc.jpg"),
                new Product(null, "Смартфон Apple iPhone 14 Pro Max 1000 ГБ", 169239, "Смартфон Apple iPhone 14 Pro Max на 1000 гигабайт защищен от пыли и влаги по стандарту IP68. Оснащен тремя тыловыми камерами и может снимать видео в формате 4К. Обладает 3-кратным оптическим зумом, а также 15-кратным цифровым.", "https://c.dns-shop.ru/thumb/st1/fit/500/500/fe8ea87e1cc00f90cfee61e30ab43923/a87566e1a761e1c090ea9f430097c6b7513f7dfffb8344890c6ee14ea956bd0f.jpg"),
                new Product(null, "Samsung Galaxy S23 Ultra 1000 ГБ", 139999, "Смартфон Samsung Galaxy S23 Ultra 1000 ГБ – модель флагманской серии с дисплеем 6.8 дюйма и поддержкой внесения данных при помощи фирменного стилуса. Панель с частотой обновления кадров 120 Гц и технологией Dynamic AMOLED 2X способна впечатлять реалистичной картинкой с богатой цветовой палитрой. Профессиональная камера с 4-мя датчиками 200+12+10+10 Мп способна создавать четкие и красочные снимки. Фронтальная камера 12 Мп и множество творческих режимов позволяют делать креативные селфи.\n" +
                        "Быстродействие Samsung Galaxy S23 Ultra при выполнении различных задач обеспечивается процессором Snapdragon 8 и 12 ГБ ОЗУ. Корпус смартфона надежно защищен от внешних воздействий по стандарту IP68. В устройстве реализованы стандарты беспроводной синхронизации Wi-Fi и Bluetooth, модуль NFC, стереодинамики. Аккумулятор 5000 мА*ч гарантирует длительное время работы без подзарядки, а также отличается поддержкой быстрой и беспроводной зарядки.", "https://c.dns-shop.ru/thumb/st1/fit/500/500/1fecb481857b110024873d0efd10e2fc/3725db021d8585c16dbbadacede3bde87c1ed04c755f44134a80ea4352725116.jpg"),
                new Product(null, "Tecno Phantom V Fold 512 ГБ", 89999, "Смартфон Tecno Phantom V Fold работает на процессоре MediaTek Dimensity 9000+ в тандеме с 12 ГБ оперативной памяти и 512 ГБ встроенной. Cнаружи «фантом» оснащен 6,42-дюймовым AMOLED-дисплеем с разрешением FHD+, внутри — 7,85-дюймовым с разрешением 2К (тоже AMOLED).\n" +
                        "Модуль основной камеры имеет круглую форму и состоит из трех датчиков: 50 Мп (основной) + 50 Мп (телеобъектив) и 12 Мп (сверхширокоугольный).\n" +
                        "За автономность отвечает аккумуляторная батарея емкостью 5000 мА*ч с поддержкой зарядки мощностью 45 Вт. Cмартфон поддерживает работу в сетях 5G.", "https://c.dns-shop.ru/thumb/st1/fit/500/500/bc3e2cdb427e034fe62170dfd54e79b5/a46fb0ee00f49a4cab94299b6f8e43e8e2e21647c22aea7c429625f71c93a727.jpg"),
                new Product(null, "Смартфон OnePlus 10T 128 ГБ", 49990, "Смартфон OnePlus 10T оснащен 6.7-дюймовым AMOLED-дисплеем с незаметными глазом рамками. Вы отметите естественную цветопередачу и быструю смену кадров при демонстрации насыщенных эпизодов. Основная камера с 3 модулями и разрешением матрицы 50 Мп может похвастаться наличием опций на базе искусственного интеллекта. Они автоматически корректируют кадры, усиливая детализацию и яркость.\n" +
                        "Аппаратная часть смартфона OnePlus 10T состоит из 8-ядерного процессора и 8 ГБ оперативной памяти. Вы будете удивлены мгновенному запуску приложений и навигации между ними без малейших задержек. Просмотр личной информации возможен после верификации личности по чертам лица и отпечатку пальца. Молниеносно восполнить энергию аккумулятора поможет поддержка быстрой зарядки SuperVOOC 150 Вт.", "https://c.dns-shop.ru/thumb/st1/fit/500/500/92b0536d24f32910cc41d8cbef63aa78/a55c3bad08017a410631f2c1a9129320390e3f3e2c56e2d959c7f3a8da65abd6.jpg"),
                new Product(null, "HUAWEI Mate 50 256 ГБ", 49999, "Смартфон HUAWEI Mate 50 поддерживает работу в сети 5G и характеризуется высокой производительностью. Модель позволяет использовать две SIM-карты, а 8-ядерный процессор с частотой отклика 90 Гц обеспечивает плавный переход и моментальную загрузку данных. На широком экране диагональю 6.7 дюймов удобно смотреть фильмы, ролики, страницы в интернете или читать книги. OLED-дисплей передает мельчайшие и яркие детали, сохраняя естественные оттенки без искажений.\n" +
                        "Смартфон HUAWEI Mate 50 черного цвета изготовлен из высокопрочного металла и стекла, поэтому устойчив к воздействию влаги, пыли и механическим повреждениям. Объем памяти составляет 256 ГБ, хранилище можно увеличить на столько же за счет карты MicroSD. Тройная камера 50+13+12 Мп позволяет делать снимки профессионального качества, передавая естественную глубину цвета и резкость. В зависимости от условий съемки диафрагма устанавливается автоматически, делая объект максимально реалистичным.", "https://c.dns-shop.ru/thumb/st1/fit/500/500/42fbbeec2b5dda03d40808ad8551d6f9/d3ac145b04a0760eeaa86709885ed739c208c1809a1bb834fb2cff5d0ff47914.jpg"),
                new Product(null, "Смартфон POCO F5 Pro 256 ГБ",47999 , "POCO F5 Pro основан на чипе Snapdragon 8+ Gen 1. За охлаждение отвечает большая испарительная камера. Смартфон получил 6,67-дюймовый AMOLED-дисплей с разрешением WQHD+ (3200 × 1440 пикселей) и кадровой частотой 120 Гц. Защиту дисплея обеспечивает стекло Gorilla Glass 5.\n" +
                        "Тыльная камера тройная, с главным сенсором Omnivision OV64B на 64 Мп с OIS, шириком на 8 Мп и макрокамерой на 2 Мп. Селфи-камера на 16 Мп. Смартфон оснащен батареей на 5160 мАч с поддержкой 67-ваттной проводной зарядкой и 30-ваттной — беспроводной. Имеются модули NFC и Bluetooth 5.3. Работает устройство на Android 13 с MIUI 14. Объем ОЗУ — 12 ГБ, флеш-памяти — 256 ГБ. Смартфон весит 204 г, его толщина — 8,6 мм.", "https://c.dns-shop.ru/thumb/st1/fit/500/500/253c2eb530bfee2485e3827658fcb79a/fe0c91c9a60e37aff24906cd8f9006016829baf1d91c8d2c041824dfe2f00a3e.jpg"),
                new Product(null, "Смартфон POCO F4 GT 128 ГБ", 45990 , "Смартфон POCO F4 GT 128 ГБ привлекает внимание ярким желтым корпусом из металла и стекла, который легко очищается от пыли и не боится повреждений. Безрамочный экран 6.67” дополнен прочным защитным стеклом, на котором не остаются царапины. Вы можете одновременно работать с двумя NanoSIM-картами с поддержкой 5G. 128 ГБ собственной памяти не потребует установки внешнего накопителя.\n" +
                        "Прибор POCO F4 GT 128 ГБ особенно понравится любителям игр благодаря 8-ядерному процессору с ОЗУ 8 ГБ под управлением Android 12. Также вас не оставят равнодушными 3 основные камеры (64+8+2 Мп), способные делать эффектные фото и 4K-видео, мощная фронтальная камера на 20 Мп. Установлены системы жидкостного охлаждения и поддержки быстрой зарядки, 3 микрофона и 4 стереодинамика.", "https://c.dns-shop.ru/thumb/st4/fit/500/500/581e98ad7176fed23e4eaa90cc8df18a/ffe0b919c1d0ee9a052eff4e8cb77ab9bc6b12b213448741bf19736109631063.jpg"),
                new Product(null, "Xiaomi 13 Lite 256 ГБ", 38999 , "Смартфон Xiaomi 13 Lite 256 ГБ выделяется безрамочным дисплеем и классическим оформлением в тонком корпусе. Стильный и компактный он в то же время отличается широкими функциональными возможностями для общения и мобильных развлечений. Мощные аппаратные компоненты обеспечивают быструю, отзывчивую и стабильную работу системы – это процессор Snapdragon 7 Gen 1 и 8 ГБ ОЗУ. На экране 6.55 дюйма AMOLED с частотой 120 Гц отображается детализированное изображение.\n" +
                        "Основная камера с тремя датчиками 50+8+2 Мп способна запечатлевать яркие, четкие и реалистичные снимки. Спереди установлена двойная камера 32+8 Мп с функцией размытия заднего фона, разными режимами и AI бьютификацией. Среди других особенностей Xiaomi 13 Lite – поддержка двух карт SIM, сканер отпечатков пальцев в экране, аудиосистема с технологией Dolby Atmos. Аккумулятор 4500 мА*ч позволяет смартфону работать автономно длительное время и поддерживает ускоренную зарядку.", "https://c.dns-shop.ru/thumb/st1/fit/500/500/3bbcffc83d74076331ae8803e7cbfa9c/625b20540099b05ac1caaac963714158ba8d4a7e2788e1ee4dec5ad2e54b29c6.jpg")
        );

        productService.addAll(list);

    }


}
